package fr.emn.fil.reservation.model.dao.jpa;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */


public abstract class AbstractJpaDAO<T> {

    protected JPAManager jpaManager;

    private final Class<T> className;

    public AbstractJpaDAO(Class<T> className) {
        jpaManager = jpaManager.getInstance();
        this.className = className;
    }

    public void save(T toSave) {
        EntityTransaction tx = jpaManager.getTransaction();
        jpaManager.getEm().persist(toSave);
        tx.commit();
    }

    public void update(T toUpdate) {
        EntityTransaction tx = jpaManager.getTransaction();
        tx.begin();
        jpaManager.getEm().merge(toUpdate);
        tx.commit();
    }

    public void delete(T toDelete) {
        EntityTransaction tx = jpaManager.getTransaction();
        jpaManager.getEm().remove(toDelete);
        tx.commit();
    }

    public T byId(Object id) {
        T found = jpaManager.getEm().find(className, id);
        return found;
    }

    public abstract List<T> findAll();

}
