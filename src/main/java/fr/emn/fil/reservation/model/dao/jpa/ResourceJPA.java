package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ResourceJPA extends AbstractJpaDAO<Resource,Long> implements ResourceDAO {

    public ResourceJPA() {
        super(Resource.class);
    }

    @Override
    public List<Resource> findAll() {
        Query q = JPAManager.getEm().createNamedQuery("resource.findAll");
        List<Resource> ressources = q.getResultList();
        return ressources;
    }

    @Override
    public List<Resource> findAvailable(Date startDate, Date endDate) {
        Query q = JPAManager.getEm().createNamedQuery("resource.findAvailable");
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        return q.getResultList();
    }

    @Override
    public List<Resource> findByTypeAndName(ResourceType type, String name) {
        Long typeId = type != null ? type.getId() : null;
        String query = JPAFilter.create(JPAFilter.FilterType.JOIN, "type.id", typeId)
                .add(JPAFilter.FilterType.STRING, "name", name)
                .getQuery("Resource");
        Query q = JPAManager.getEm().createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Resource> findByOwner(Long id) {
        String query = "SELECT res FROM Resource res WHERE res.owner = '" + id + "'";
        Query q = JPAManager.getEm().createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<Resource> findByType(ResourceType type) {
        Query q = JPAManager.getEm().createNamedQuery("resource.byType");
        q.setParameter("type", type);
        return q.getResultList();
    }
}
