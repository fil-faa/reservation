package fr.emn.fil.reservation.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class JPAManager {

    public final static String PERSISTENCE_UNIT_NAME = "reservationUnit";

    private static JPAManager instance;

    private EntityManager em;

    public static JPAManager getInstance() {
        return instance == null ?
                instance = new JPAManager() :
                instance;
    }

    public JPAManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        emf.close();
    }

    public EntityManager getEm() {
        return em;
    }

    public EntityTransaction getTransaction() {
        return em.getTransaction();
    }
}
