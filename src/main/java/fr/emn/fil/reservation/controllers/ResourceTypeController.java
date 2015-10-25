package fr.emn.fil.reservation.controllers;


import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.exceptions.ValidationError;
import fr.emn.fil.reservation.model.services.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Scanner;

public class ResourceTypeController extends Controller {

    private ResourceTypeService typeService;

    public ResourceTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        typeService = new ResourceTypeService();
    }

    @Override
    protected Response handle(String url) {
        Response response = null;
        try {

            if (request.getMethod().equals("GET")) {

                if (url.equals("/"))
                    response = getResourceTypes();

                if (url.equals("/delete"))
                    response = deleteResourceType();

                if (url.length() > 1 && response == null) {
                    Scanner scId = new Scanner(url.trim().substring(1));

                    if (scId.hasNextLong()) {
                        Long id = scId.nextLong();
                        response = editResourceTypes(id);
                    } else {
                        response = getResourceTypes();
                    }
                }

            } else if (request.getMethod().equals("POST")) {

                if (url.equals("/"))
                    response = addResourceType();

                if (url.length() > 1) {
                    Scanner scId = new Scanner(url.trim().substring(1));

                    if (scId.hasNextLong()) {
                        Long id = scId.nextLong();
                        response = editResourceTypesSave(id);
                    } else {
                        response = getResourceTypes();
                    }
                }
            }

            if (response == null)
                response = new Response("not-found.jsp", Response.Type.FORWARD);

            return response;

        } catch (GenericError e) {
            return this.getResourceTypes();
        }
    }

    private Response editResourceTypesSave(Long id) {
        try {
            ResourceType resourceType = typeService.byId(id);

            String name = request.getParameter("name");
            resourceType.setName(name);

            typeService.save(resourceType);
        } catch (ModelError modelError) {
            request.setAttribute("error", new ModelError("Type de ressource inexistant"));
        }
        return this.getResourceTypes();
    }

    private Response editResourceTypes(Long id) {
        try {
            ResourceType resourceType = typeService.byId(id);

            request.setAttribute("resourceType", resourceType);
            return new Response("/resourceType/edit.jsp", Response.Type.FORWARD);
        } catch (ModelError modelError) {
            request.setAttribute("error", new ModelError("Type de ressource inexistant"));
            return this.getResourceTypes();
        }
    }

    public Response addResourceType() {
        try {
            String name = request.getParameter("name");
            new StringValidator(name, "nom de la ressource").notEmpty();

            ResourceType ressourceType = typeService.create(name);
            request.setAttribute("resourceType", ressourceType);
            return this.getResourceTypes();

        } catch (GenericError e) {
            request.setAttribute("error", e);
        }
        return this.getResourceTypes();
    }

    public Response getResourceTypes() {
        List<ResourceType> ressourceTypes = typeService.findAll();
        request.setAttribute("resourceTypes", ressourceTypes);
        return new Response("/resourceType/index.jsp", Response.Type.FORWARD);
    }

    public Response deleteResourceType() throws GenericError {
        Long typeId = null;
        try {
            typeId = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            typeId = null;
        }
        if (typeId == null) {
            throw new ValidationError("Le type que vous voulez supprimer n'existe pas ou est corrompu");
        }

        typeService.delete(typeId);
        return this.getResourceTypes();
    }

}

