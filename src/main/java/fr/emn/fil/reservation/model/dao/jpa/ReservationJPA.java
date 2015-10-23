package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;

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

    public List<Reservation> byUser(Long userId) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.byUser");
        q.setParameter("user", userId);
        return q.getResultList();
    }

    public List<Reservation> during(Resource resource, Date start, Date end) {
        Query q = JPAManager.getEm().createNamedQuery("reservation.during");
        q.setParameter("resource", resource);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }
}
