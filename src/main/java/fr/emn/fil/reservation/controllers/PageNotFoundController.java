package fr.emn.fil.reservation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Generic action called when no page is related to the given URL
 * @author Alexandre LEBRUN
 * @see fr.emn.fil.reservation.controllers.Controller
 */
public class PageNotFoundController extends Controller {

    private static final int STATUS_404 = 404;

    public PageNotFoundController(final HttpServletRequest request, final HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String subUrl) {
        request.setAttribute("status", STATUS_404);
        return new Response("not-found.jsp", Response.Type.FORWARD);
    }
}
