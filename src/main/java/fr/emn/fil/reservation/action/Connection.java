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
    protected String handle(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if(userName == null || password == null) return "connect.jsp";

        User user = new UserService().connect(userName, password);
        if(user == null)
            return "connect.jsp";

        request.setAttribute("user", user);
        return "dashboard.jsp";
    }

}
