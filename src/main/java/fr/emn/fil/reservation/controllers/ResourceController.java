package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.controllers.validation.StringValidator;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ValidationError;
import fr.emn.fil.reservation.model.services.ResourceService;
import fr.emn.fil.reservation.model.services.ResourceTypeService;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

        } else if(request.getMethod().equals("POST")) {

            if(subUrl.equals("/"))
                response = createResource();

        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response getResources() {
        List<Resource> resources = new ResourceService().findAll();
        request.setAttribute("resources", resources);
        request.setAttribute("resourceTypes", new ResourceTypeService().findAll());
        return new Response("resources/index.jsp", Response.Type.FORWARD);
    }

    public Response createResource() {
        try {
            Long userId;
            Long typeId;
            try {
                userId = Long.parseLong(request.getParameter("userId"));
                typeId = Long.parseLong(request.getParameter("typeId"));
            } catch (NumberFormatException e) {
                throw new ValidationError("Erreur de récupération des ids");
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
