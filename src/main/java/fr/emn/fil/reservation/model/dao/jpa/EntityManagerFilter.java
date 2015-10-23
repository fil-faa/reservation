package fr.emn.fil.reservation.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by alexa on 24/10/2015.
 */
public class EntityManagerFilter implements Filter {

    public final static String PERSISTENCE_UNIT_NAME = "reservationUnit";

    private static EntityManagerFactory entityManagerFactory = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            destroy();
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            JPAManager.ENTITY_MANAGER.set(em);
            filterChain.doFilter(servletRequest, servletResponse);
            JPAManager.ENTITY_MANAGER.remove();
        } finally {
            try {
                if(em != null)
                    em.close();
            } catch(Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public void destroy() {
        if(entityManagerFactory != null)
            entityManagerFactory.close();
    }
}
