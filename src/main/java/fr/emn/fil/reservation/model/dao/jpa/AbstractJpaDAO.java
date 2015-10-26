package fr.emn.fil.reservation.model.dao.jpa;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */

/**
 * Abstract class handling general CRUD operations on the JPA entities
 *
 * @param <T> Entity class which we want to query
 * @param <I> ID column type
 */
public abstract class AbstractJpaDAO<T, I> {

    private final Class<T> className;

    public AbstractJpaDAO(Class<T> className) {
        this.className = className;
    }

    public void save(T toSave) {
        try {
            EntityTransaction tx = JPAManager.getTransaction();
            tx.begin();
            JPAManager.getEm().persist(toSave);
            tx.commit();
        } finally {
            if (JPAManager.getTransaction().isActive())
                JPAManager.getTransaction().rollback();
        }
    }

    public void update(T toUpdate) {
        try {
            EntityTransaction tx = JPAManager.getTransaction();
            tx.begin();
            JPAManager.getEm().merge(toUpdate);
            tx.commit();
        } finally {
            if (JPAManager.getTransaction().isActive())
                JPAManager.getTransaction().rollback();
        }
    }

    public void delete(T toDelete) {
        try {
            EntityTransaction tx = JPAManager.getTransaction();
            tx.begin();
            JPAManager.getEm().remove(toDelete);
            tx.commit();
        } finally {
            if (JPAManager.getTransaction().isActive())
                JPAManager.getTransaction().rollback();
        }
    }

    public T byId(I id) {
        return JPAManager.getEm().find(className, id);
    }

    public abstract List<T> findAll();

}
