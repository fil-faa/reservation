package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.controllers.validation.LongValidator;
import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.exceptions.ValidationError;
import fr.emn.fil.reservation.model.services.ReservationService;
import fr.emn.fil.reservation.model.services.ResourceService;
import fr.emn.fil.reservation.model.services.ResourceTypeService;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xela on 21/10/15.
 */
public class ResourceController extends Controller {

    private ResourceService resourceService;
    private UserService userService;
    private ResourceTypeService resourceTypeService;

    public ResourceController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        this.userService = new UserService();
        this.resourceTypeService = new ResourceTypeService();
        this.resourceService = new ResourceService();
    }

    @Override
    protected Response handle(String subUrl)  {
        Response response = null;
        if(request.getMethod().equals("GET")) {

            if(subUrl.equals("/"))
                response = getResources();

            if(subUrl.equals("/delete"))
                response = deleteResource();

            if (subUrl.length() > 1 && response == null) {
                Scanner scId = new Scanner(subUrl.trim().substring(1));

                if (scId.hasNextLong()) {
                    Long id = scId.nextLong();
                    response = editResource(id);
                } else {
                    response = getResources();
                }
            }

        } else if(request.getMethod().equals("POST")) {

            if(subUrl.equals("/"))
                response = createResource();

            if (subUrl.length() > 1 && response == null) {
                Scanner scId = new Scanner(subUrl.trim().substring(1));

                if (scId.hasNextLong()) {
                    Long id = scId.nextLong();
                    response = editResourceSave(id);
                } else {
                    response = getResources();
                }
            }
        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }

    private Response editResourceSave(Long id) {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            Resource resource = resourceService.byId(id);

            String name = request.getParameter("name");
            if (!name.equals(resource.getName()) && !name.isEmpty()) {
                resource.setName(name);
            }
            String place = request.getParameter("place");
            if (!place.equals(resource.getPlace()) && !place.isEmpty()) {
                resource.setPlace(place);
            }
            String description = request.getParameter("description");
            if (!description.equals(resource.getDescription()) && !description.isEmpty()) {
                resource.setDescription(description);
            }
            String typeIdDStr = request.getParameter("typeId");
            if (!typeIdDStr.equals(resource.getType().getId().toString()) && !typeIdDStr.isEmpty()) {
                Long typeId = Long.valueOf(typeIdDStr);
                ResourceType type = new ResourceTypeService().byId(typeId);
                resource.setType(type);
            }

            resourceService.save(resource);
        } catch (ModelError modelError) {
            request.setAttribute("error", "Ressource inexistante");
        }
        return this.getResources();
    }

    private Response editResource(Long id) {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            Resource resource = resourceService.byId(id);

            request.setAttribute("resource", resource);
            request.setAttribute("resourceTypes", new ResourceTypeService().findAll());
            return new Response("resources/edit.jsp", Response.Type.FORWARD);
        } catch (ModelError modelError) {
            request.setAttribute("error", "Resource inexistant");
            return this.getResources();
        }
    }


    public Response getResources() {
        Long searchedType;
        String searchedName = request.getParameter("searchedName");
        try {
            searchedType = Long.parseLong(request.getParameter("searchedType"));
        } catch(Exception e) {
            searchedType = null;
        }

        try {
            List<Resource> found;
            boolean filterByType = searchedType != null;
            boolean filterByName = searchedName != null && !searchedName.isEmpty();

            if(filterByType && filterByType) {
                ResourceType type = resourceTypeService.byId(searchedType);
                found = resourceService.findbyTypeAndName(type, searchedName);
            } else if(filterByType) {
                ResourceType type = resourceTypeService.byId(searchedType);
                found = resourceService.findByType(type);
            } else if(filterByName){
                found = resourceService.findByName(searchedName);
            } else {
                found = resourceService.findAll();
            }
            request.setAttribute("resources", found);
        } catch(GenericError e) {
            request.setAttribute("error", e);
            return this.getResources();
        }
        request.setAttribute("resourceTypes", new ResourceTypeService().findAll());
        return new Response("resources/index.jsp", Response.Type.FORWARD);
    }

    public Response deleteResource() {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            Long resourceId = new LongValidator("id").parse(request.getParameter("id")).get();
            Resource resource = resourceService.byId(resourceId);
            resourceService.delete(resource);
        } catch(GenericError e) {
            request.setAttribute("error", e);
        }
        finally {
            return getResources();
        }
    }

    public Response createResource() {
        if(nonAdminRedirect()!=null) return nonAdminRedirect();

        try {
            Long userId;
            Long typeId;
            try {
                userId = Long.parseLong(request.getParameter("userId"));
                typeId = Long.parseLong(request.getParameter("typeId"));
            } catch (NumberFormatException e) {
                throw new ValidationError("Erreur de r�cup�ration des ids");
            }

            String name = request.getParameter("name");
            new StringValidator(name, "nom").notEmpty().minLength(3).maxLength(255);

            String place = request.getParameter("place");
            new StringValidator(place, "description").notEmpty().minLength(3).maxLength(100);

            String description = request.getParameter("description");
            new StringValidator(description, "description").maxLength(255);

            User user = userService.byId(userId);
            ResourceType resourceType = resourceTypeService.byId(typeId);
            resourceService.create(name, user, resourceType, place, description);
        } catch(GenericError e) {
            request.setAttribute("error", e);
        }
        finally {
            return this.getResources();
        }
    }
}
