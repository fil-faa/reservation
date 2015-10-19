package fr.emn.fil.reservation.action;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandre on 12/10/2015.
 */
public class Connection extends Action {

    @Override
    protected String handle(HttpServletRequest request) {
        return "connect.jsp";
    }

}
