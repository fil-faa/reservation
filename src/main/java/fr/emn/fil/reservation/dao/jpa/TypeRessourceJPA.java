package fr.emn.fil.reservation.dao.jpa;

import fr.emn.fil.reservation.dao.TypeRessourceDAO;
import fr.emn.fil.reservation.entities.TypeRessource;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class TypeRessourceJPA extends AbstractJpaDAO<TypeRessource> implements TypeRessourceDAO{

    public TypeRessourceJPA() {
        super(TypeRessource.class);
    }

    public List<TypeRessource> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("typeRessource.findAll");
        List<TypeRessource> types = q.getResultList();
        return types;
    }

}
