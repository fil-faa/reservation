package fr.emn.fil.reservation.dao;

import fr.emn.fil.reservation.dao.jpa.ReservationJPA;
import fr.emn.fil.reservation.dao.jpa.RessourceJPA;
import fr.emn.fil.reservation.dao.jpa.TypeRessourceJPA;
import fr.emn.fil.reservation.dao.jpa.UserJPA;
import fr.emn.fil.reservation.entities.Reservation;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class DAOFactory {

    public static ReservationDAO reversationDAO() {
        return new ReservationJPA();
    }

    public static RessourceDAO ressourceDAO() {
        return new RessourceJPA();
    }

    public static UserDAO userDAO() {
        return new UserJPA();
    }

    public static TypeRessourceDAO typeRessourceDAO() {
        return new TypeRessourceJPA();
    }

}
