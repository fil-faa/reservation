package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.services.ResourceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xela on 21/10/15.
 */
public class ResourceController extends Controller {

    public ResourceController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String subUrl) throws GenericError {
        Response response = null;
        if(request.getMethod().equals("GET")) {

            if(subUrl.equals("/"))
                response = getResources();

        } else if(request.getMethod().equals("POST")) {

        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response getResources() {
        List<Resource> resources = new ResourceService().findAll();
        request.setAttribute("resources", resources);
        return new Response("resources/index.jsp", Response.Type.FORWARD);
    }
}
