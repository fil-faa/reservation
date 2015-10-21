package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class UserController extends Controller {

    public UserController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String url) {

        Response response = null;
        if(request.getMethod().equals("GET")) {

            if(url.equals("/login"))
                response = login();

            if(url.equals("/add"))
                response = addUserForm();

            if(url.equals("/"))
                response = getUsers();

        } else if(request.getMethod().equals("POST") && url.equals("/add")) {
            response = addUser();
        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }

    public Response login() {
        return new Response("/reservations/", Response.Type.REDIRECT);
    }

    public Response addUser() {
        try {
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");


            User user = new UserService().create(mail, password, firstName, lastName, phone);
            request.setAttribute("user", user);
            return new Response("/reservations/", Response.Type.REDIRECT);

        } catch(fr.emn.fil.reservation.model.exceptions.Error e) {
            request.setAttribute("error", e);
            return new Response("/users/add", Response.Type.FORWARD);
        }
    }

    public Response getUsers() {
        List<User> users = new UserService().findAll();
        request.setAttribute("users", users);
        return new Response("/users/index.jsp", Response.Type.FORWARD);
    }

    public Response addUserForm() {

        return  new Response("/users/add.jsp", Response.Type.FORWARD);
    }
}
