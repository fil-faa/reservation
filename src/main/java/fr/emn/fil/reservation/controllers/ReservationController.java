package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.controllers.validation.LongValidator;
import fr.emn.fil.reservation.model.UserManager;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.GenericSuccess;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.services.ReservationService;
import fr.emn.fil.reservation.model.services.ResourceService;
import fr.emn.fil.reservation.model.services.ResourceTypeService;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationController extends Controller {

    private ResourceService resourceService;

    private ReservationService reservationService;

    private ResourceTypeService resourceTypeService;

    private UserService userService;

    public ReservationController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        resourceService = new ResourceService();
        reservationService = new ReservationService();
        resourceTypeService = new ResourceTypeService();
        userService = new UserService();
    }

    @Override
    protected Response handle(String url) {
        request.setAttribute("menuUserClass", "info");
        request.setAttribute("menuReservationsClass", "info active");
        request.setAttribute("menuMesReservationsClass", "info");
        request.setAttribute("menuReservationsAdminClass", "info");
        request.setAttribute("menuResourceClass", "info");
        request.setAttribute("menuResourceTypeClass", "info");
        request.setAttribute("menuResourceRechercheClass", "info");

        Response response = null;
        if (request.getMethod().equals("GET")) {

            if (url.equals("/search")) {
                response = searchAvailableResources();
                request.setAttribute("menuResourceRechercheClass", "info active");
            }

            if (url.length() > 1 && response == null) {
                Scanner scId = new Scanner(url.trim().substring(1));
                if (scId.hasNextLong()) {
                    Long id = scId.nextLong();
                    response = getUserReservations(id);
                    request.setAttribute("menuMesReservationsClass", "info active");
                } else {
                    response = searchAvailableResources();
                }
            }
            if (url.equals("/")) {
                response = getAllReservations();
                request.setAttribute("menuReservationsAdminClass", "info active");
            }
            if(url.equals("/cancel"))
                response = cancelReservation();

        } else if (request.getMethod().equals("POST")) {

            if (url.equals("/"))
                response = book();

        }

        if (response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }

    public Response cancelReservation() {
        User user;           // we ensure that the connected user owns the reservation
        Long reservationId;  // id of the reservation to cancel
        try {
            user = UserManager.getCurrentUser();
        } catch(ModelError e) {
            return new Response( ROOT_URL + "/users/login", Response.Type.REDIRECT);
        }
        try {
            reservationId = new LongValidator("id de réservation")
                    .parse(request.getParameter("reservationId"))
                    .get();
            reservationService.cancel(user, reservationId);
            request.setAttribute("success", new GenericSuccess("La réservation d'identifiant " + reservationId + " a bien été supprimée."));
        } catch(GenericError e) {
            request.setAttribute("error", e);
        }
        return this.getUserReservations(user.getId());
    }

    public Response getAllReservations() {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();
        Long typeId=null;
        Long userId=null;
        try {
            typeId = Long.parseLong(request.getParameter("searchedType"));
        } catch(NumberFormatException e) {

        String FDDFSD = e.getMessage();
        }
        try {
            userId = Long.parseLong(request.getParameter("searchedUser"));
        } catch(NumberFormatException e) {

            String dsqd = e.getMessage();
        }

        String rangeString = request.getParameter("searchRange");

        Date[] range = parseRange(rangeString);

        String resourceName = request.getParameter("searchedName");


        List<ResourceType> types = resourceTypeService.findAll();
            request.setAttribute("types", types);
            List<User> users = userService.findAll();
            request.setAttribute("users", users);
            List<Reservation> reservations = reservationService.findAllDuring(userId,typeId,resourceName,range[0],range[1]);
            request.setAttribute("reservations", reservations);

        return new Response("/reservation/index.jsp", Response.Type.FORWARD);
    }

    public Response getUserReservations(Long idUser) {
        try {

            User user = UserManager.getCurrentUser();
            if(!user.isAdmin() && !user.getId().equals(idUser) ) return  nonAdminRedirect();
            user = userService.byId(idUser);
            Long typeId;
            ResourceType type = null;
            try {
                typeId = Long.parseLong(request.getParameter("searchedTypeId"));
                type = resourceTypeService.byId(typeId);
            } catch(NumberFormatException e) { }
            String name = request.getParameter("searchedName");

            List<ResourceType> types = resourceTypeService.findAll();
            request.setAttribute("types", types);
            List<Reservation> reservations = reservationService.filter(user, type, name);
            request.setAttribute("reservations", reservations);
        } catch(GenericError e) { // if the user is not found in the session, we redirect to the login page
            return nonAdminRedirect();
        }
        return new Response("/reservation/singleton.jsp", Response.Type.FORWARD);
    }

    public Response book() {
        // Parse the reservation range
        String rangeString = request.getParameter("reservationRange");
        Date[] range = parseRange(rangeString);

        try {

            // Get the resource id
            Long resourceId;
            try {
                resourceId = Long.parseLong(request.getParameter("resourceId"));
            } catch (NumberFormatException e) {
                resourceId = null;
            }
            Resource resource = resourceService.byId(resourceId);
            User user = UserManager.getCurrentUser();

            reservationService.create(range[0], range[1], resource, user);

        } catch(GenericError e) {
            request.setAttribute("error", e);
            return this.searchAvailableResources();
        }
        try {
            return this.getUserReservations(UserManager.getCurrentUser().getId());
        } catch (ModelError modelError) {
            modelError.printStackTrace();
        }
        return nonAdminRedirect();
    }

    public Response searchAvailableResources() {
        initSpan();
        String rangeString = request.getAttribute("searchRange").toString();
        String searchedName = request.getParameter("searchedName");

        // parse the selected type
        Long typeId;
        try {
            typeId = Long.parseLong(request.getParameter("typeId"));
        } catch(NumberFormatException e) {
            typeId = null;
        }

        // parse the date range
        Date[] range = parseRange(rangeString);

        // fetch the resources for the selected ranges
        List<Resource> resources = resourceService.findAvailableResources(range[0], range[1], typeId,searchedName);
        request.setAttribute("resources", resources);

        // add the resource types for the view
        List<ResourceType> types = resourceTypeService.findAll();
        request.setAttribute("types", types);

        return new Response("/reservation/search.jsp", Response.Type.FORWARD);
    }

    /**
     * Parses the date range according to a specific pattern (@see view)
     * @param range Range given by the HTTP POST request
     * @return an array containing the begin and end date of the range
     */
    private Date[] parseRange(String range) {
        Date[] dates = new Date[2];
        dates[0] = dates[1] = null;
        if(range == null) return dates;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            if(range != null) {
                String start = range.substring(0, 16);
                String end = range.substring(19);
                dates[0] = sdf.parse(start);
                dates[1] = sdf.parse(end);
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return dates;
    }

    private void initSpan() {
        String range = request.getParameter("searchRange");
        if(range == null) {
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
            range = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            range += " - " + sdf.format(calendar.getTime());
            request.setAttribute("searchRange", range);
        } else {
            request.setAttribute("searchRange", request.getParameter("searchRange"));
        }
    }

}
