package fr.emn.fil.reservation.dao.jpa;

import fr.emn.fil.reservation.dao.RessourceDAO;
import fr.emn.fil.reservation.entities.Ressource;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class RessourceJPA extends AbstractJpaDAO<Ressource> implements RessourceDAO {

    public RessourceJPA() {
        super(Ressource.class);
    }

    public List<Ressource> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("ressource.findAll");
        List<Ressource> ressources = q.getResultList();
        return ressources;
    }


}
