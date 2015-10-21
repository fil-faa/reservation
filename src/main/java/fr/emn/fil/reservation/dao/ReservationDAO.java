package fr.emn.fil.reservation.dao;

import fr.emn.fil.reservation.entities.Reservation;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface ReservationDAO {


    List<Reservation> findAll();

    void save(Reservation toSave);

    void update(Reservation toUpdate);

    void delete(Reservation toDelete);


}
