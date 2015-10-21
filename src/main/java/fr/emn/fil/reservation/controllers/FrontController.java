package fr.emn.fil.reservation.controllers;


public class FrontController extends Controller {

    @Override
    protected Response handle(String subUrl) {

        return new Response("dashboard.jsp", Response.Type.FORWARD);
    }


}
