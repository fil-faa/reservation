package fr.emn.fil.reservation.controllers;


import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceTypeController extends Controller {

    public ResourceTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String url) throws ModelError {

        Response response = null;
        if (request.getMethod().equals("GET")) {

            if (url.equals("/login"))
                response = loginForm();

            if (url.equals("/"))
                response = getResourceTypes();

        } else if (request.getMethod().equals("POST")) {

            if (url.equals("/login"))
                response = login();

            if (url.equals("/"))
                response = addResourceType();
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
        } catch (ModelError e) {
            request.setAttribute("error", e);
            return new Response("users/connect.jsp", Response.Type.FORWARD);
        }
        return new Response("/reservations/reservations", Response.Type.REDIRECT);

    }

    public Response loginForm() {
        return new Response("users/connect.jsp", Response.Type.FORWARD);
    }

    public Response addResourceType() {
        try {
            String name = request.getParameter("name");
            if (name == null || name.length() == 0)
                throw new ModelError("Veuillez rentrer le nom du type de ressource");

            RessourceType ressourceType = new RessourceTypeService().create(name);
            request.setAttribute("ressourceType", ressourceType);
            return new Response("/reservations/", Response.Type.REDIRECT);

        } catch (ModelError e) {
            request.setAttribute("error", e);
            return new Response("/resourceType/index.jsp", Response.Type.FORWARD);
        }
    }

    public Response getResourceTypes() {
        List<RessourceType> ressourceTypes = new RessourceType().findAll();
        request.setAttribute("ressourceTypes", ressourceTypes);
        return new Response("/ressourceType/index.jsp", Response.Type.FORWARD);
    }

}

