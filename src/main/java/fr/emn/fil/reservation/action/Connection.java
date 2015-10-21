package fr.emn.fil.reservation.action;

import fr.emn.fil.reservation.dao.DAOFactory;
import fr.emn.fil.reservation.dao.jpa.UserJPA;
import fr.emn.fil.reservation.entities.User;
import fr.emn.fil.reservation.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexandre on 12/10/2015.
 */
public class Connection extends Action {

    @Override
    protected Response handle(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if(userName == null || password == null) return new Response("connect.jsp", Response.Type.FORWARD);

        User user = new UserService().connect(userName, password);
        if(user == null)
            return new Response("connect.jsp", Response.Type.FORWARD);

        request.setAttribute("user", user);
        return new Response("dashboard.jsp", Response.Type.FORWARD);
    }

}
