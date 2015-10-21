package fr.emn.fil.reservation.controllers;

/**
 * Created by Alexandre on 12/10/2015.
 */
public class Connection extends Controller {

    @Override
    protected Response handle(String subUrl) {
//        String userName = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if(userName == null || password == null) return new Response("connect.jsp", Response.Type.FORWARD);
//
//        User user = new UserService().connect(userName, password);
//        if(user == null)
//            return new Response("connect.jsp", Response.Type.FORWARD);
//
//        request.setAttribute("user", user);
        return new Response("dashboard.jsp", Response.Type.FORWARD);
    }

}
