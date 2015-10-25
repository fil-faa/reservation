package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = DAOFactory.reservationDAO();
    }

    public List<Reservation> findAll() {
        return reservationDAO.findAll();
    }

    public List<Reservation> byUser(User user) {
        return reservationDAO.byUser(user);
    }

    public Reservation create(Date startDate, Date endDate, Resource resource, User user) throws GenericError {

        // We must avoid ubiquity for the resources
        List<Reservation> existing = reservationDAO.during(resource, startDate, endDate);
        if(existing.size() > 0)
            throw new GenericError("Cette ressource est d�j� r�serv�e");

        Reservation reservation = new Reservation(startDate, endDate, resource, user);
        reservationDAO.save(reservation);
        return reservation;
    }

    public void cancel(User user, Long id) throws GenericError {
        Reservation reservation = reservationDAO.byId(id);
        if(reservation == null) throw new GenericError("La r�servation que vous voulez supprimer n'existe plus");


        if(reservation.getUser().equals(user))
            reservationDAO.delete(reservation);
        else throw new ModelError("Erreur d'annulation : vous essayer de supprimer une r�servation qui ne vous appartient pas");
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

    public List<Reservation> getFutureReservationByResource(Resource resource) {
        List<Reservation> reservations = byResource(resource);
        ArrayList<Reservation> futureReservation = new ArrayList<>();

        for(Reservation reservation : reservations) {
            if (reservation.getStatus())
                futureReservation.add(reservation);
        }

        return futureReservation;
    }
}
