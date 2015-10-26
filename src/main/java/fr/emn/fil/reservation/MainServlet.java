package fr.emn.fil.reservation;

import fr.emn.fil.reservation.controllers.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre on 09/10/2015.
 * Front controller. It receives all the requests of the application, and redirects it to the correct action
 */
public class MainServlet extends HttpServlet {

    public void handleAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Controller> routes = new HashMap<>();

        routes.put("/resources", new ResourceController(req, resp));
        routes.put("/users", new UserController(req, resp));
        routes.put("/reservations", new ReservationController(req, resp));
        routes.put("/resourceTypes", new ResourceTypeController(req, resp));

        Controller toExecute = null;

        String subRoute = "";
        for(String route : routes.keySet() ) {
            if(req.getPathInfo() != null && req.getPathInfo().startsWith(route)) {
                toExecute = routes.get(route);
                subRoute = req.getPathInfo().substring(route.length());
                break;
            }
        }

        if(toExecute == null) toExecute = new PageNotFoundController(req, resp);


        toExecute.execute(subRoute);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp);
    }

}
