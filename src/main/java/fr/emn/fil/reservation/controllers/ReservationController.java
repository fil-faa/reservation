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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ReservationController extends Controller {

    private ResourceService resourceService;

    private ReservationService reservationService;

    private ResourceTypeService resourceTypeService;

    public ReservationController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        resourceService = new ResourceService();
        reservationService = new ReservationService();
        resourceTypeService = new ResourceTypeService();
    }

    @Override
    protected Response handle(String url) {
        request.setAttribute("menuUserClass", "info");
        request.setAttribute("menuReservationsClass", "info active");
        request.setAttribute("menuResourceClass", "info");
        request.setAttribute("menuResourceTypeClass", "info");
        request.setAttribute("menuResourceRechercheClass", "info");

        Response response = null;
        if (request.getMethod().equals("GET")) {

            if (url.equals("/search")) {
                response = searchAvailableResources();
                request.setAttribute("menuResourceRechercheClass", "info active");
                request.setAttribute("menuReservationsClass", "info");
            }

            if (url.equals("/"))
                response = getReservations();

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
            reservationId = new LongValidator("id de r�servation")
                    .parse(request.getParameter("reservationId"))
                    .get();
            reservationService.cancel(user, reservationId);
            request.setAttribute("success", new GenericSuccess("La r�servation d'identifiant " + reservationId + " a bien �t� supprim�e."));
        } catch(GenericError e) {
            request.setAttribute("error", e);
        }
        return this.getReservations();
    }

    public Response getReservations() {
        try {
            User user = UserManager.getCurrentUser();
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
            return new Response(ROOT_URL +"/users/login", Response.Type.REDIRECT);
        }
        return new Response("/reservation/index.jsp", Response.Type.FORWARD);
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
        return this.getReservations();
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
