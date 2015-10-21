package fr.emn.fil.reservation;

import fr.emn.fil.reservation.controllers.Controller;
import fr.emn.fil.reservation.controllers.PageNotFoundController;
import fr.emn.fil.reservation.controllers.UserController;
import fr.emn.fil.reservation.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre on 09/10/2015.
 * Front controller. It receives all the requests of the application, and redirects it to the correct action
 */
public class MainServlet extends HttpServlet {


    public void handleAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Controller> routes = new HashMap<String, Controller>();

        routes.put("/users", new UserController(req, resp));

        Controller toExecute = null;

        String subRoute = "";
        for(String route : routes.keySet() ) {
            if(req.getPathInfo().startsWith(route)) {
                toExecute = routes.get(route);
                subRoute = req.getPathInfo().substring(route.length());
                break;
            }
        }

        if(toExecute == null) toExecute = new PageNotFoundController(req, resp);


        toExecute.execute("/WEB-INF/index.jsp", subRoute);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp);
    }

    /**
     * Gets the user's auth status by watching the sessions attributes
     * @param request HTTP request, containing the session
     * @return UserDTO's auth status. True if he is connected
     */
    public boolean isConnected(HttpServletRequest request) {
        // We get the current session. If no session exists, we do not create it
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("user") instanceof User)
            return true;
        return false;

    }
}
