package fr.emn.fil.reservation.action;

import fr.emn.fil.reservation.entities.User;

import javax.servlet.http.HttpServletRequest;

import static fr.emn.fil.reservation.entities.User.getUser;


public class Dashboard extends Action {

    @Override
    protected String handle(HttpServletRequest request) {

//        User user1 = getUser(1);
        request.setAttribute("test",getUser(1));
//        System.out.println();
        return "dashboard.jsp";
    }
}
