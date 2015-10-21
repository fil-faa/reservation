package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;
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
    protected Response handle(String url) throws ModelError {

        Response response = null;
        if(request.getMethod().equals("GET")) {

            if(url.equals("/login"))
                response = loginForm();

            if(url.equals("/"))
                response = getUsers();

        } else if(request.getMethod().equals("POST")) {

            if(url.equals("/login"))
                response = login();

            if(url.equals("/"))
                response = addUser();
        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response login() {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            User user = new UserService().connect(mail, password);
            request.setAttribute("user", user);
        } catch(ModelError e) {
            request.setAttribute("error", e);
            return new Response("users/connect.jsp", Response.Type.FORWARD);
        }
        return new Response("/reservations/reservations", Response.Type.REDIRECT);

    }

    public Response loginForm() {
        return new Response("users/connect.jsp", Response.Type.FORWARD);
    }

    public Response addUser() {
        try {
            String mail = request.getParameter("mail");
            if(mail == null || mail.length() == 0   || !mail.contains("@"))
                throw new ModelError("Veuillez fournir une adresse email valide");
            String password = request.getParameter("password");
            if(password == null || password.length() < 8 )
                throw new ModelError("Veuillez fournir un mot de passe d'une taille minimum de 8 caractères");
            String phone = request.getParameter("phone");
            if(phone == null || phone.length() == 0)
                throw new ModelError("Veuillez fournir un numéro de téléphone valide");
            String firstName = request.getParameter("firstName");
            if(firstName == null || firstName.length() == 0)
                throw new ModelError("Veuillez rentrer votre prénom");
            String lastName = request.getParameter("lastName");
            if(mail == null || mail.length() == 0)
                throw new ModelError("Veuillez rentrer votre nom");

            User user = new UserService().create(mail, password, firstName, lastName, phone);
            request.setAttribute("user", user);
            return new Response("/reservations/", Response.Type.REDIRECT);

        } catch(ModelError e) {
            request.setAttribute("error", e);
            return new Response("/users/index.jsp", Response.Type.FORWARD);
        }
    }

    public Response getUsers() {
        List<User> users = new UserService().findAll();
        request.setAttribute("users", users);
        return new Response("/users/index.jsp", Response.Type.FORWARD);
    }

}
