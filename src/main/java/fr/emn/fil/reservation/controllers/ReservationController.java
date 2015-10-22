package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.services.ReservationService;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationController extends Controller {

    public ReservationController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String url) {

        Response response = null;
        if (request.getMethod().equals("GET")) {

            if (url.equals("/login"))
                response = loginForm();

            if (url.equals("/"))
                response = getReservations();

        } else if (request.getMethod().equals("POST")) {

            if (url.equals("/login"))
                response = login();

            if (url.equals("/")) {
                List<Resource> resources = DAOFactory.resourceDAO().findAll();
                List<User> users = DAOFactory.userDAO().findAll();

                request.setAttribute("resources", resources);
                request.setAttribute("users", users);
                response = addReservation();
            }
        }

        if (response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response login() {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            User user = new UserService().connect(mail, password);
            request.setAttribute("user", user);
        } catch (GenericError e) {
            request.setAttribute("error", e);
            return new Response("users/connect.jsp", Response.Type.FORWARD);
        }
        return new Response("/reservations/reservations", Response.Type.REDIRECT);

    }

    public Response loginForm() {
        return new Response("users/connect.jsp", Response.Type.FORWARD);
    }

    public Response addReservation() {
        try {
            String strStartDate = request.getParameter("startDate");
            DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
            Date startDate = formatter.parse(strStartDate);
            if (startDate == null)
                throw new GenericError("Veuillez fournir une date de début correcte");
            String strEndDate = request.getParameter("endDate");
            Date endDate = formatter.parse(strEndDate);
            if (endDate == null)
                throw new GenericError("Veuillez fournir une date de fin correcte");
            Long resourceId = Long.valueOf(request.getParameter("resourceId"));
            Resource resource = DAOFactory.resourceDAO().byId(resourceId);
            Long userId = Long.valueOf(request.getParameter("userId"));
            User user = DAOFactory.userDAO().byId(userId);

            Reservation reservation = new ReservationService().create(startDate, endDate, resource, user);
            request.setAttribute("reservation", reservation);
            return new Response(request.getContextPath() + "/reservations/users/", Response.Type.REDIRECT);

        } catch (GenericError e) {
            request.setAttribute("error", e);
            return new Response("/reservation/index.jsp", Response.Type.FORWARD);
        } catch (ParseException e) {
            request.setAttribute("error", e);
            return new Response("/reservation/index.jsp", Response.Type.FORWARD);
        }
    }

    public Response getReservations() {
        List<Reservation> reservations = new ReservationService().findAll();
        request.setAttribute("reservations", reservations);
        return new Response("/reservation/index.jsp", Response.Type.FORWARD);
    }

}
