package fr.emn.fil.reservation.controllers;


import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.services.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResourceTypeController extends Controller {

    public ResourceTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String url) throws GenericError {

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
            new StringValidator(name, "nom de la ressource").notEmpty();

            ResourceType ressourceType = new ResourceTypeService().create(name);
            request.setAttribute("ressourceType", ressourceType);
            return this.getResourceTypes();

        } catch (GenericError e) {
            request.setAttribute("error", e);
        }
        return this.getResourceTypes();
    }

    public Response getResourceTypes() {
        List<ResourceType> ressourceTypes = new ResourceTypeService().findAll();
        request.setAttribute("ressourceTypes", ressourceTypes);
        return new Response("/ressourceType/index.jsp", Response.Type.FORWARD);
    }

}

