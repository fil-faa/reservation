package fr.emn.fil.reservation.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends Controller {

    public FrontController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    protected Response handle(String subUrl) {

        return new Response("dashboard.jsp", Response.Type.FORWARD);
    }


}
