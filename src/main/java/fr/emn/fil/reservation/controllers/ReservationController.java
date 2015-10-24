package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.UserManager;
import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.services.ReservationService;
import fr.emn.fil.reservation.model.services.ResourceService;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController extends Controller {

    private ResourceService resourceService;

    private ReservationService reservationService;


    public ReservationController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        resourceService = new ResourceService();
        reservationService = new ReservationService();
    }

    @Override
    protected Response handle(String url) {

        Response response = null;
        if (request.getMethod().equals("GET")) {

            if (url.equals("/search"))
                response = searchAvailableResources();

            if (url.equals("/"))
                response = getReservations();

        } else if (request.getMethod().equals("POST")) {

            if (url.equals("/"))
                response = book();

        }

        if (response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response getReservations() {
        List<Reservation> reservations = new ReservationService().findAll();
        request.setAttribute("reservations", reservations);
        return new Response("/reservation/index.jsp", Response.Type.FORWARD);
    }

    public Response book() {
        // Parse the reservation range
        String rangeString = request.getParameter("reservationRange");
        Date[] range = parseRange(rangeString);

        try {

            // Get the resource id
            Long resourceId = null;
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
        String rangeString = request.getParameter("searchRange");
        Date[] range = parseRange(rangeString);
        if(range[0] == null || range[1] == null) {
            request.setAttribute("resources", new ArrayList<Resource>());
        } else {
            List<Resource> resources = resourceService.findAvailableResources(range[0], range[1]);
            request.setAttribute("resources", resources);
        }
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(range != null) {
                String start = range.substring(0, 10);
                String end = range.substring(13);
                dates[0] = sdf.parse(start);
                dates[1] = sdf.parse(end);
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return dates;
    }

}
