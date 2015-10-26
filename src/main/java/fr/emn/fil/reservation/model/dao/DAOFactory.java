package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.dao.jpa.ReservationJPA;
import fr.emn.fil.reservation.model.dao.jpa.ResourceJPA;
import fr.emn.fil.reservation.model.dao.jpa.ResourceTypeJPA;
import fr.emn.fil.reservation.model.dao.jpa.UserJPA;

/**
 * Factory storing the current implementations of the DAOs
 */
public class DAOFactory {

    public static ResourceDAO resourceDAO() {
        return new ResourceJPA();
    }

    public static UserDAO userDAO() {
        return new UserJPA();
    }

    public static ResourceTypeDAO resourceTypeDAO() {
        return new ResourceTypeJPA();
    }

    public static ReservationDAO reservationDAO() {
        return new ReservationJPA();
    }
}
