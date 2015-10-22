package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;

import javax.persistence.Query;
import java.util.List;

public class ReservationJPA extends AbstractJpaDAO<Reservation> implements ReservationDAO {

    public ReservationJPA() {
        super(Reservation.class);
    }

    public List<Reservation> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("reservation.findAll");
        List<Reservation> reservations = q.getResultList();
        return reservations;
    }

    public Reservation byId(Long reservationId) {
        Query q = jpaManager.getEm().createNamedQuery("reservation.byId");
        q.setParameter("id", reservationId);
        Reservation reservation = (Reservation) q.getSingleResult();
        return reservation;
    }

}
