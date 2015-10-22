package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.exceptions.GenericError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Generic action called when no page is related to the given URL
 * Created by Alexandre on 10/10/2015.
 */
public class PageNotFoundController extends Controller {

    public PageNotFoundController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String subUrl) {
        request.setAttribute("status", 404);
        return new Response("not-found.jsp", Response.Type.FORWARD);
    }
}
