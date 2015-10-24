package fr.emn.fil.reservation.controllers;

import fr.emn.fil.reservation.model.UserManager;
import fr.emn.fil.reservation.model.entities.User;

import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alexa on 24/10/2015.
 */
public class LoginFilter implements Filter {

    public static String LOGIN_URL = "/book/users/login";
    public static String ROOT_URL = "/book";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        destroy();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        try {

            if (session != null && session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                UserManager.users.set(user);
                filterChain.doFilter(request, response);
                UserManager.users.remove();
            } else {
                String requestURI = request.getRequestURI();
                if (requestURI.startsWith(ROOT_URL) && !requestURI.equals(LOGIN_URL)) {
                    response.sendRedirect(request.getContextPath() + LOGIN_URL);
                } else filterChain.doFilter(request, response);
            }

        } catch(IOException | ClassCastException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
