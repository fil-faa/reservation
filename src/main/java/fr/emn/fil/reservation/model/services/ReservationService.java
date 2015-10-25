package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.*;

public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = DAOFactory.reservationDAO();
    }

    public ReservationService(ReservationDAO dao) {
        this.reservationDAO = dao;
    }

    public List<Reservation> findAll() {
        return reservationDAO.findAll();
    }

    public List<Reservation> byUser(User user) {
        return reservationDAO.byUser(user);
    }

    public Reservation create(Date startDate, Date endDate, Resource resource, User user) throws ModelError {
        // rule : startDate < endDate
        if(startDate.after(endDate))
            throw new ModelError("La date de début de la réservation doit précéder sa date de fin");

        if(beginningOfDay(startDate) < beginningOfDay(new Date()))
            throw new ModelError("Impossible d'effectuer une réservation dans le passé");

        // We must avoid ubiquity for the resources
        List<Reservation> existing = reservationDAO.during(resource, startDate, endDate);
        if(existing.size() > 0)
            throw new ModelError("Cette ressource est déjà réservée");

        Reservation reservation = new Reservation(startDate, endDate, resource, user);
        reservationDAO.save(reservation);
        return reservation;
    }

    public void cancel(User user, Long id) throws ModelError {
        Reservation reservation = reservationDAO.byId(id);
        if(reservation == null) throw new ModelError("La réservation que vous voulez supprimer n'existe plus");


        if(reservation.getUser().equals(user))
            reservationDAO.delete(reservation);
        else throw new ModelError("Erreur d'annulation : vous essayer de supprimer une réservation qui ne vous appartient pas");
    }

    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

    public List<Reservation> filter(User user, ResourceType type, String name) {
        return reservationDAO.matching(user, type, name);
    }

    public List<Reservation> byResource(Resource resource) {
        return reservationDAO.byResource(resource);
    }

    private Long beginningOfDay(Date startDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }
}
