package fr.emn.fil.reservation.controllers;

/**
 * Generic action called when no page is related to the given URL
 * Created by Alexandre on 10/10/2015.
 */
public class PageNotFoundController extends Controller {

    @Override
    protected Response handle(String subUrl) {
        request.setAttribute("status", 404);
        return new Response("not-found.jsp", Response.Type.FORWARD);
    }
}
