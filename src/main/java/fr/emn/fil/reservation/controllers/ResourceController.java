package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.entities.Ressource;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.services.ResourceService;

import java.util.List;

/**
 * Created by xela on 21/10/15.
 */
public class ResourceController extends Controller {

    @Override
    protected Response handle(String subUrl) throws ModelError {
        Response response = null;
        if(request.getMethod().equals("GET")) {

            if(url.equals("/"))
                response = getUsers();

        } else if(request.getMethod().equals("POST")) {

        }

        if(response == null)
            response = new Response("not-found.jsp", Response.Type.FORWARD);

        return response;
    }


    public Response getResources() {
        List<Ressource> resources = new ResourceService().findAll();
        request.setAttribute("resources", resources);
        return new Response("resources/index.jsp", Response.Type.FORWARD);
    }
}
