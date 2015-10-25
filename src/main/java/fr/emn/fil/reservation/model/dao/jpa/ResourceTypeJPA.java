package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ResourceTypeDAO;
import fr.emn.fil.reservation.model.entities.ResourceType;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class ResourceTypeJPA extends AbstractJpaDAO<ResourceType,Long> implements ResourceTypeDAO {

    public ResourceTypeJPA() {
        super(ResourceType.class);
    }

    public List<ResourceType> findAll() {
        Query q = JPAManager.getEm().createNamedQuery("resourceType.findAll");
        List<ResourceType> types = q.getResultList();
        return types;
    }

    public List<ResourceType> byName(String name) {
        Query q = JPAManager.getEm().createNamedQuery("resourceType.byNameLike");
        q.setParameter("name", "%" + name.toUpperCase() + "%");
        List<ResourceType> types = q.getResultList();
        return types;
    }

}
