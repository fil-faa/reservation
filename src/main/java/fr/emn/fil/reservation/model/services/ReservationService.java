package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.User;

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

    public Reservation create(Date startDate, Date endDate, Resource resource, User user) {
        Reservation reservation = new Reservation(startDate, endDate, resource, user);
        reservationDAO.save(reservation);
        return reservation;
    }

    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }
}
