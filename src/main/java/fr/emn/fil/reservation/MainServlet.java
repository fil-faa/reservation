package fr.emn.fil.reservation;

import fr.emn.fil.reservation.action.Action;
import fr.emn.fil.reservation.action.PageNotFound;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Action> routes = new HashMap<String, Action>();

        Action toExecute = routes.get(req.getPathInfo());

        if(toExecute == null) toExecute = new PageNotFound();

        toExecute.execute("/WEB-INF/index.jsp", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
