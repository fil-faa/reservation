package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Reservation;

import java.util.List;

public interface ReservationDAO {


    List<Reservation> findAll();

    void save(Reservation toSave);

    void update(Reservation toUpdate);

    void delete(Reservation toDelete);


    Reservation byId(Long reservationId);

    List<Reservation> findByUser(Long userId);
}
