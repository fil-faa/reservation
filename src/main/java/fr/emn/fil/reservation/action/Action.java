package fr.emn.fil.reservation.action;

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
public abstract class Action {

    /**
     * Abstract method permitting to define what needs to be done after action calling
     * @param request HTTP arguments of the request
     * @return URL of the page to display after execution
     */
    protected abstract String handle(HttpServletRequest request);

    /**
     * Executes the action, and then redirects to the correct page
     * @param endpoint URL of generic page
     * @param request HTTP arguments of the request
     * @param response The future HTTP response
     * @throws IOException
     * @throws ServletException Thrown when a servlet error occurs
     */
    public void execute(String endpoint, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = handle(request);
        request.setAttribute("page", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(endpoint);
        requestDispatcher.forward(request, response);
    }


}
