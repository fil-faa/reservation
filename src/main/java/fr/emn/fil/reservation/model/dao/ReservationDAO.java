package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Reservation;

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
