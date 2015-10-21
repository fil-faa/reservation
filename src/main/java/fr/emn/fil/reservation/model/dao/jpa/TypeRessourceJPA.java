package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.TypeRessourceDAO;
import fr.emn.fil.reservation.model.entities.ResourceType;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class TypeRessourceJPA extends AbstractJpaDAO<ResourceType> implements TypeRessourceDAO {

    public TypeRessourceJPA() {
        super(ResourceType.class);
    }

    public List<ResourceType> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("resourceType.findAll");
        List<ResourceType> types = q.getResultList();
        return types;
    }

}
