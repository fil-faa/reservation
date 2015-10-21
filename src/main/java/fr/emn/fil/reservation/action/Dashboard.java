package fr.emn.fil.reservation.action;

import fr.emn.fil.reservation.entities.User;

import javax.servlet.http.HttpServletRequest;

import static fr.emn.fil.reservation.entities.User.getUser;


public class Dashboard extends Action {

    @Override
    protected Response handle(HttpServletRequest request) {
        return new Response("dashboard.jsp", Response.Type.FORWARD);
    }
}
