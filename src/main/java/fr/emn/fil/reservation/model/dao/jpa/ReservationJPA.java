package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.dao.jpa.JPAFilter.FilterType;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ReservationJPA extends AbstractJpaDAO<Reservation,Long> implements ReservationDAO {

    public ReservationJPA() {
        super(Reservation.class);
    }

    public List<Reservation> findAll() {
        Query q = JPAManager.getEm().createNamedQuery("reservation.findAll");
        return q.getResultList();
    }

    public List<Reservation> during(Resource resource, Date start, Date end) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.during");
        q.setParameter("resource", resource);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }

    @Override
    public List<Reservation> byUser(User user) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.byUser");
        q.setParameter("user", user);
        return q.getResultList();
    }

    /**
     * Dynamic query handling cumulative filters on reservations
     * @param user filter by user (nullable)
     * @param type filter by type (nullable)
     * @param name filter by name (nullable)
     * @return
     */
    @Override
    public List<Reservation> matching(User user, ResourceType type, String name) {
        String query = JPAFilter.create(FilterType.JOIN, "user.id", user.getId())
                .add(FilterType.JOIN, "type.id", type.getId())
                .add(FilterType.STRING, "resource.name", name)
                .getQuery("Resource");

        Query q = JPAManager.getEm().createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Reservation> byResource(Resource resource) {
        String query = "SELECT res FROM Reservation res WHERE res.resource.id = '" + resource.getId() + "'";
        Query q = JPAManager.getEm().createQuery(query);
        return q.getResultList();
    }

}
