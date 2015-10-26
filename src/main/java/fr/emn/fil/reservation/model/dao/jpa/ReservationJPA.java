package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.dao.jpa.JPAFilter.FilterType;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * JPA implementation of the reservation DAO
 * @see ReservationDAO
 */
@SuppressWarnings("ALL")
public class ReservationJPA extends AbstractJpaDAO<Reservation,Long> implements ReservationDAO {

    public ReservationJPA() {
        super(Reservation.class);
    }

    public List<Reservation> findAll() {
        Query q = JPAManager.getEm().createNamedQuery("reservation.findAll");
        return q.getResultList();
    }

    /**
     * @see ReservationDAO
     */
    public List<Reservation> during(Resource resource, Date start, Date end) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.during");
        q.setParameter("resource", resource);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }

    @Override
    public List<Reservation> during(Date start, Date end) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.findAllDuring");
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();    }

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
        Long typeId = type == null ? null : type.getId();
        String query = JPAFilter.create(FilterType.JOIN, "user.id", user.getId())
                .add(FilterType.JOIN, "type.id", typeId)
                .add(FilterType.STRING, "resource.name", name)
                .getQuery("Reservation");

        Query q = JPAManager.getEm().createQuery(query);
        return q.getResultList();
    }


}
