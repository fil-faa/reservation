package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class RessourceJPA extends AbstractJpaDAO<Resource> implements ResourceDAO {

    public RessourceJPA() {
        super(Resource.class);
    }

    public List<Resource> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("resource.findAll");
        List<Resource> ressources = q.getResultList();
        return ressources;
    }


}
