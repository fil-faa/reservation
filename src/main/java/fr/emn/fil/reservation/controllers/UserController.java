package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
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
        try {
            if (request.getMethod().equals("GET")) {

                if (url.equals("/login"))
                    response = loginForm();

                if (url.equals("/delete"))
                    response = deleteUser();

                if (url.equals("/"))
                    response = getUsers();

            } else if (request.getMethod().equals("POST")) {

                if (url.equals("/login"))
                    response = login();

                if (url.equals("/"))
                    response = addUser();
            }

            if (response == null)
                response = new Response("not-found.jsp", Response.Type.FORWARD);

            return response;

        } catch (GenericError e) {
            return this.getUsers();
        }
    }


    public Response login() {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            User user = new UserService().connect(mail, password);
            request.getSession().setAttribute("user", user);
        } catch(GenericError e) {
            request.setAttribute("error", e);
            return this.loginForm();
        }
        return new Response("/reservations/reservations/", Response.Type.REDIRECT);
    }

    public Response loginForm() {
        return new Response("users/connect.jsp", Response.Type.FORWARD);
    }
    public Response deleteUser() throws GenericError
    {
        Long userId=null;
        try {
            userId = Long.parseLong(request.getParameter("id"));
            if(userId == null) throw new NumberFormatException();
        } catch(NumberFormatException e) {
            throw new GenericError("Cet utilisateur ne peut ?tre supprim? : erreur syst?me");
        }
       try
        {
            new UserService().delete(userId);
        }
        catch (GenericError e)
        {
            request.setAttribute("error", e);
            return getUsers();
        }
        return getUsers();
    }
    public Response addUser() {
        try {
            String mail = request.getParameter("mail");
            new StringValidator(mail, "E-mail").notEmpty().mustContain("@");

            String password = request.getParameter("password");
            new StringValidator(password, "mot de passe").minLength(8).maxLength(250);

            String phone = request.getParameter("phone");
            new StringValidator(phone, "téléphone").mustBeNumeric();

            String firstName = request.getParameter("firstName");
            new StringValidator(firstName, "prénom").notEmpty();

            String lastName = request.getParameter("lastName");
            new StringValidator(firstName, "nom").notEmpty();

            User user = new UserService().create(mail, password, firstName, lastName, phone);
            request.setAttribute("user", user);
            return new Response(request.getContextPath() + "/reservations/users/", Response.Type.REDIRECT);

        } catch(GenericError e) {
            request.setAttribute("error", e);
            return getUsers();
        }
    }

    public Response getUsers() {
        List<User> users = new UserService().findAll();
        request.setAttribute("users", users);
        return new Response("/users/index.jsp", Response.Type.FORWARD);
    }

}
