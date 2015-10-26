package fr.emn.fil.reservation.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Singleton which store the entityManager with a <code>ThreadLocal</code>
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
