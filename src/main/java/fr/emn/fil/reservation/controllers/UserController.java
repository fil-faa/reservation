package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.GenericSuccess;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Scanner;

import static fr.emn.fil.reservation.CryptUtils.hash;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class UserController extends Controller {

    private UserService userService;

    public UserController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        userService = new UserService();
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

                if (url.length() > 1 && response == null) {
                    Scanner scId = new Scanner(url.trim().substring(1));

                    if (scId.hasNextLong()) {
                        Long id = scId.nextLong();
                        response = editUser(id);
                    } else {
                        response = getUsers();
                    }
                }

            } else if (request.getMethod().equals("POST")) {

                if (url.equals("/login"))
                    response = login();

                if (url.equals("/"))
                    response = addUser();

                if (url.length() > 1 && response == null) {
                    Scanner scId = new Scanner(url.trim().substring(1));

                    if (scId.hasNextLong()) {
                        Long id = scId.nextLong();
                        response = editUserSave(id);
                    } else {
                        response = getUsers();
                    }
                }
            }

            if (response == null)
                response = new Response("not-found.jsp", Response.Type.FORWARD);

            return response;

        } catch (GenericError e) {
            return this.getUsers();
        }
    }

    private Response editUserSave(Long id) {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            User user = userService.byId(id);

            String lastName = request.getParameter("lastName");
            if (!lastName.equals(user.getLastName()) && !lastName.isEmpty()) {
                user.setLastName(lastName);
            }
            String firstName = request.getParameter("firstName");
            if (!firstName.equals(user.getFirstName()) && !firstName.isEmpty()) {
                user.setFirstName(firstName);
            }
            String mail = request.getParameter("mail");
            if (!mail.equals(user.getMail()) && !mail.isEmpty()) {
                user.setMail(mail);
            }
            String phone = request.getParameter("phone");
            if (!phone.equals(user.getTelephone()) && !phone.isEmpty()) {
                user.setTelephone(phone);
            }
            String password = request.getParameter("password");
            if (!hash(password).equals(user.getPassword()) && !password.isEmpty()) {
                user.setPassword(hash(password));
            }

            userService.save(user);
        } catch (ModelError modelError) {
            request.setAttribute("error", new ModelError("Utilisateur inexistant"));
        }
        return this.getUsers();
    }

    private Response editUser(Long id) {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            User user = userService.byId(id);

            request.setAttribute("user", user);
            return new Response("users/edit.jsp", Response.Type.FORWARD);
        } catch (ModelError modelError) {
            request.setAttribute("error", new ModelError("Utilisateur inexistant"));
            return this.getUsers();
        }
    }


    public Response login() {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            User user = userService.connect(mail, password);
            request.getSession().setAttribute("user", user);
        } catch(GenericError e) {
            request.setAttribute("error", e);
            return this.loginForm();
        }
        return new Response(ROOT_URL + "/reservations/", Response.Type.REDIRECT);
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
            throw new GenericError("Cet utilisateur ne peut être supprimé : erreur système");
        }
       try
        {
            if (userService.isUserDeletable(userId)) {
                userService.delete(userId);
            } else {
                request.setAttribute("error", new ModelError("Impossible de supprimer l'utilisateur"));
            }
        }
        catch (GenericError e)
        {
            request.setAttribute("error", e);
            return getUsers();
        }
        request.setAttribute("success", new GenericSuccess("L'utilisateur d'identifiant "
                + userId + " a bien été supprimé."));
        return getUsers();
    }
    public Response addUser() {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

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

            User user = userService.create(mail, password, firstName, lastName, phone);
            request.setAttribute("user", user);
            request.setAttribute("success", new GenericSuccess("L'utilisateur " + user.getFirstName() + " "
                    + user.getLastName() + " a bien été créé."));
            return new Response(ROOT_URL + "/users/", Response.Type.REDIRECT);

        } catch(GenericError e) {
            request.setAttribute("error", e);
            return getUsers();
        }
    }

    public Response getUsers() {
        String mail = request.getParameter("searchedMail");
        String firstName = request.getParameter("searchedFirstName");
        String lastName = request.getParameter("searchedLastName");
        String phone = request.getParameter("searchedPhone");

        List<User> users;
        if(mail == null && firstName == null && lastName == null && phone == null){
            users = userService.findAll();
        } else {
            users = userService.filter(firstName, lastName, mail, phone);
        }

        request.setAttribute("users", users);
        return new Response("/users/index.jsp", Response.Type.FORWARD);
    }

}
