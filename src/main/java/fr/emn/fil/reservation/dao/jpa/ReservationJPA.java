package fr.emn.fil.reservation.dao.jpa;

import fr.emn.fil.reservation.dao.ReservationDAO;
import fr.emn.fil.reservation.entities.Reservation;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */

public class ReservationJPA extends AbstractJpaDAO<Reservation> implements ReservationDAO {

    public ReservationJPA() {
        super(Reservation.class);
    }

    public List<Reservation> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("reservation.findAll");
        List<Reservation> reservations = q.getResultList();
        return reservations;
    }

}
