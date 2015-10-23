package fr.emn.fil.reservation.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class JPAManager {

    public static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<EntityManager>();

    public static EntityManager getEm() {
        return ENTITY_MANAGER.get();
    }

    public static EntityTransaction getTransaction() {
        return getEm().getTransaction();
    }
}
