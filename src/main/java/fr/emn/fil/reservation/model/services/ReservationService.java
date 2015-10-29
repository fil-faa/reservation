package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
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

    /**
     * Search and filter the reservations over a given period
     * @param userId the reservations' user id (nullable)
     * @param typeId resource type of the booked products (nullable)
     * @param resourceName reservation name (nullable)
     * @param startDate start of the period
     * @param endDate end of the period
     * @return
     */
    public List<Reservation> findAllDuring(Long userId,Long typeId,String resourceName,Date startDate, Date endDate) {

        List<Reservation> reservations = reservationDAO.during(startDate,endDate);
        if(startDate == null || endDate == null) reservations= reservationDAO.findAll();
        if(userId==null && typeId==null && resourceName==null)return reservations;
        Iterator<Reservation> it = reservations.iterator();
        while(it.hasNext()) {
            Reservation reservation = it.next();
            boolean delete=false;
            if(userId!=null)
                if(!reservation.getUser().getId().equals(userId))
                    delete=true;
            if(typeId!=null)
                if(!reservation.getResource().getType().getId().equals(typeId))
                    delete=true;
            if(resourceName != null)
                if(!reservation.getResource().getName().toLowerCase().contains(resourceName.toLowerCase()))
                    delete=true;
            if(delete)
                it.remove();
        }
        return reservations;
    }

    /**
     * Book a resource for a specific period
     * @param startDate beginning of the period
     * @param endDate ending of the period
     * @param resource the resource to book
     * @param user the user who book
     * @return the created <code>Reservation</code>
     */
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

    /**
     * A reservation can be deleted from the database
     * @param user user who wants to remove the reservation
     * @param id id of the reservation to delete
     * @throws ModelError thrown if the reservations doesn't exist or if the user has not the rights
     */
    public void cancel(User user, Long id) throws ModelError {
        Reservation reservation = reservationDAO.byId(id);
        if(reservation == null) throw new ModelError("La réservation que vous voulez supprimer n'existe plus");


        if(reservation.getUser().equals(user) || user.isAdmin())
            reservationDAO.delete(reservation);
        else throw new ModelError("Erreur d'annulation : vous essayer de supprimer une réservation qui ne vous appartient pas");
    }

    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

    public List<Reservation> filter(User user, ResourceType type, String name) {
        return reservationDAO.matching(user, type, name);
    }

    /**
     * Set the date to the beginning of the day
     * @param startDate date to update
     */
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
