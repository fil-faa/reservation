package fr.emn.fil.reservation.action;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class AddUser extends Action {

    @Override
    protected Response handle(HttpServletRequest request) {



        return new Response("/users", Response.Type.REDIRECT);
    }
}
