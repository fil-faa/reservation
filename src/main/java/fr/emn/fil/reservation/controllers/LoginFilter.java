package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.UserManager;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.services.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter intercepting the unauthorized requests (the user is not logged)
 * Created by alexa on 24/10/2015.
 * @see javax.servlet.Filter
 */
public class LoginFilter implements Filter {

    public static final String LOGIN_URL = "/book/users/login";
    public static final String ROOT_URL = "/book";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        destroy();
    }

    /**
     * This filter handles user's authentification by retrieving the logged user in the session
     * @param req HTTP request
     * @param resp future HTTP response
     * @param filterChain rest of the filters which proceeds the request
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        try {

            if (session != null && session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");

                Long id = user.getId();
                try {
                    user = new UserService().byId(id);
                } catch(GenericError e) {
                    response.sendRedirect(request.getContextPath() + LOGIN_URL);
                    return;
                }

                // set the user in the singleton
                UserManager.users.set(user);
                filterChain.doFilter(request, response); // we proceed the request
                UserManager.users.remove();
            } else {
                String requestURI = request.getRequestURI();
                if (requestURI.startsWith(request.getContextPath() + ROOT_URL)
                        && !requestURI.equals(request.getContextPath() + LOGIN_URL)) {
                    response.sendRedirect(request.getContextPath() + LOGIN_URL);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
