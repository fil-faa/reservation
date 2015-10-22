package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;

import java.util.Date;
import java.util.List;

public interface ReservationDAO {


    List<Reservation> findAll();

    void save(Reservation toSave);

    void update(Reservation toUpdate);

    void delete(Reservation toDelete);

    List<Reservation> during(Resource resource, Date start, Date end);

    Reservation byId(Long reservationId);

    List<Reservation> byUser(Long userId);
}
