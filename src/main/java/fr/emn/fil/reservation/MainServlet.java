package fr.emn.fil.reservation;

import fr.emn.fil.reservation.controllers.Controller;
import fr.emn.fil.reservation.controllers.ResourceController;
import fr.emn.fil.reservation.controllers.ReservationController;
import fr.emn.fil.reservation.controllers.UserController;
import fr.emn.fil.reservation.controllers.PageNotFoundController;
import fr.emn.fil.reservation.controllers.ResourceTypeController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre on 09/10/2015.
 * Front controller. It receives all the requests of the application,
 * and redirects it to the correct action.
 */
public class MainServlet extends HttpServlet {

    /**
     * Handle all the requests prefixed by <code>ROOT_NAME</code>
     * It give the requests to one of the given controllers.
     * @param req HTTP request
     * @param resp HTTP response
     * @throws IOException case of a IO error
     * @throws ServletException
     */
    public void handleAction(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        /** Routes map, containing all the controllers */
        Map<String, Controller> routes = new HashMap<>();
        routes.put("/resources", new ResourceController(req, resp));
        routes.put("/users", new UserController(req, resp));
        routes.put("/reservations", new ReservationController(req, resp));
        routes.put("/resourceTypes", new ResourceTypeController(req, resp));

        Controller toExecute = null;

        /**
         * If we find a controller with the matching pattern, we execute its
         * <code>execute</code> method.
         */
        String subRoute = "";
        for(String route : routes.keySet()) {
            if(req.getPathInfo() != null && req.getPathInfo().startsWith(route)) {
                toExecute = routes.get(route);
                subRoute = req.getPathInfo().substring(route.length());
                break;
            }
        }

        if(toExecute == null) toExecute = new PageNotFoundController(req, resp);


        toExecute.execute("/WEB-INF/index.jsp", subRoute);
    }

    /**
     * Retrieves the GET HTTP requests.
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        this.handleAction(req, resp);
    }

    /**
     * Retrieves the POST HTTP requests
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        this.handleAction(req, resp);
    }

}
