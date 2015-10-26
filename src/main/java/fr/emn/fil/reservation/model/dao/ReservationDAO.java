package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;

import java.util.Date;
import java.util.List;

public interface ReservationDAO {

    List<Reservation> byUser(User user);

    List<Reservation> findAll();

    void save(Reservation toSave);

    void update(Reservation toUpdate);

    void delete(Reservation toDelete);

    List<Reservation> during(Resource resource, Date start, Date end);

    List<Reservation> during( Date start, Date end);

    Reservation byId(Long reservationId);

    List<Reservation> matching(User user, ResourceType type, String name);

    List<Reservation> byResource(Resource resource);
}
