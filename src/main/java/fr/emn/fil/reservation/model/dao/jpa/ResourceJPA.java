package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;

import javax.persistence.Query;
import java.util.List;

public class ResourceJPA extends AbstractJpaDAO<Resource> implements ResourceDAO {

    public ResourceJPA() {
        super(Resource.class);
    }

    public List<Resource> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("resource.findAll");
        List<Resource> ressources = q.getResultList();
        return ressources;
    }

    public Resource byId(Long resourceId) {
        Query q = jpaManager.getEm().createNamedQuery("resource.byId");
        q.setParameter("id", resourceId);
        Resource resource = (Resource)q.getSingleResult();
        return resource;
    }
}