package fr.emn.fil.reservation.controllers;

import sun.net.httpserver.HttpServerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract class permitting to define actions for a specific type of request
 * An action handles request by sending a response.
 * Once the response collected, the main servlet redirects to the view in order to render it.
 * Created by Alexandre on 09/10/2015.
 */
public abstract class Controller {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public Controller(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Abstract method permitting to define what needs to be done after action calling
     * @return URL of the page to display after execution
     */
    protected abstract Response handle(String subUrl);

    /**
     * Executes the action, and then redirects to the correct page
     * @param endpoint URL of generic page
     * @throws IOException
     * @throws ServletException Thrown when a servlet error occurs
     */
    public void execute(String endpoint, String subRoute) throws IOException, ServletException {
        Response result = handle(subRoute);
        request.setAttribute("page", result.getPage());

        // Get the page status if given
        Integer status = (Integer) request.getAttribute("status");
        // Set the correct status to the response
        if(status != null) response.setStatus(status);

        if(result.getType() == Response.Type.FORWARD) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(endpoint);
            requestDispatcher.forward(request, response);
        } else if(result.getType() == Response.Type.REDIRECT) {
            response.sendRedirect(result.getPage());
        }
    }


}