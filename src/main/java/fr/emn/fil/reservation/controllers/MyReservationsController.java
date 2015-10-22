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
import java.util.List;

public class MyReservationsController extends Controller {

    public MyReservationsController(HttpServletRequest request, HttpServletResponse response) {
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

            if (url.equals("/delete")) {
                List<Resource> resources = DAOFactory.resourceDAO().findAll();
                List<User> users = DAOFactory.userDAO().findAll();

                request.setAttribute("resources", resources);
                request.setAttribute("users", users);
                response = deleteReservation();
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
        return new Response("/reservations/reservation", Response.Type.REDIRECT);

    }

    public Response loginForm() {
        return new Response("users/connect.jsp", Response.Type.FORWARD);
    }

    public Response deleteReservation() {
            Long reservationId = Long.valueOf(request.getParameter("reservationId"));
            Reservation reservation = DAOFactory.reservationDAO().byId(reservationId);

            new ReservationService().delete(reservation);
            request.setAttribute("reservation", reservation);
            return new Response(request.getContextPath() + "/reservations/reservation/", Response.Type.REDIRECT);
    }

    public Response getReservations() {
        User user = (User) request.getAttribute("user");
        List<Reservation> reservations = new ReservationService().findByUser(user);
        request.setAttribute("reservations", reservations);
        return new Response("/myReservations/index.jsp", Response.Type.FORWARD);
    }

}
