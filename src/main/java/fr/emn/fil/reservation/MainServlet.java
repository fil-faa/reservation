package fr.emn.fil.reservation;

import fr.emn.fil.reservation.action.*;
import fr.emn.fil.reservation.dto.UserDTO;

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


    public void handleAction(HttpServletRequest req, HttpServletResponse resp, RequestType type) throws ServletException, IOException {

        Map<String, Action> getRoutes = new HashMap<String, Action>();
        Map<String, Action> postRoutes = new HashMap<String, Action>();

        // HTTP GET method routes
        getRoutes.put("/connect", new Connection());
        getRoutes.put("/dashboard", new Dashboard());

        // HTTP POST method routes
        postRoutes.put("/connect", new Connection());
        postRoutes.put("/users", new AddUser()); // TODO

        Action toExecute = null;

        switch(type) {
            case GET:
                toExecute = getRoutes.get(req.getPathInfo());
            case POST:
                toExecute = getRoutes.get(req.getPathInfo());
        }

        if(toExecute == null) toExecute = new PageNotFound();
        else if(!this.isConnected(req)) toExecute = new Connection();

        toExecute.execute("/WEB-INF/index.jsp", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp, RequestType.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleAction(req, resp, RequestType.POST);
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
