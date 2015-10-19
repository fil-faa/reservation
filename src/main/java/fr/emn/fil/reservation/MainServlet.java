package fr.emn.fil.reservation;

import fr.emn.fil.reservation.action.Action;
import fr.emn.fil.reservation.action.Connection;
import fr.emn.fil.reservation.action.Dashboard;
import fr.emn.fil.reservation.action.PageNotFound;
import fr.emn.fil.reservation.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre on 09/10/2015.
 * Front controller. It receives all the requests of the application, and redirects it to the correct action
 */
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Map<String, Action> routes = new HashMap<String, Action>();

        System.out.println("Connection received, route : " + req.getPathInfo());

        // Add routes to the application...
        // routes.put(_path_, _class_);
        routes.put("/connect", new Connection());
        routes.put("/dashboard", new Dashboard());

        Action toExecute = routes.get(req.getPathInfo());

        if(toExecute == null) toExecute = new PageNotFound();
        else if(!this.isConnected(req)) toExecute = new Connection();

        toExecute.execute("/WEB-INF/index.jsp", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * Gets the user's auth status by watching the sessions attributes
     * @param request HTTP request, containing the session
     * @return UserDTO's auth status. True if he is connected
     */
    public boolean isConnected(HttpServletRequest request) {
        // We get the current session. If no session exists, we do not create it
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("user") instanceof UserDTO)
            return true;
        return false;

    }
}
