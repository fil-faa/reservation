package fr.emn.fil.reservation.controllers;


import fr.emn.fil.reservation.model.dao.jpa.ResourceTypeJPA;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.services.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResourceTypeController extends Controller {

    public ResourceTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String url) throws ModelError {

        Response response = null;
        if (request.getMethod().equals("GET")) {


            if (url.equals("/"))
                response = getResourceTypes();

        } else if (request.getMethod().equals("POST")) {

            if (url.equals("/"))
                response = addResourceType();
        }

        if (response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }

    public Response addResourceType() {
        try {
            String name = request.getParameter("name");
            if (name == null || name.length() == 0)
                throw new ModelError("Veuillez rentrer le nom du type de ressource");

            ResourceType ressourceType = new ResourceTypeService().create(name);
            request.setAttribute("ressourceType", ressourceType);
            return new Response("/reservations/", Response.Type.REDIRECT);

        } catch (ModelError e) {
            request.setAttribute("error", e);
            return new Response("/resourceType/index.jsp", Response.Type.FORWARD);
        }
    }

    public Response getResourceTypes() {
        List<ResourceType> ressourceTypes = new ResourceTypeJPA().findAll();
        request.setAttribute("ressourceTypes", ressourceTypes);
        return new Response("/ressourceType/index.jsp", Response.Type.FORWARD);
    }

}

