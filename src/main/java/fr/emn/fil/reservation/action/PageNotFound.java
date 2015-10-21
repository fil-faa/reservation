package fr.emn.fil.reservation.action;

import javax.servlet.http.HttpServletRequest;

/**
 * Generic action called when no page is related to the given URL
 * Created by Alexandre on 10/10/2015.
 */
public class PageNotFound extends Action {

    @Override
    protected Response handle(HttpServletRequest request) {
        request.setAttribute("status", 404);
        return new Response("not-found.jsp", Response.Type.FORWARD);
    }
}
