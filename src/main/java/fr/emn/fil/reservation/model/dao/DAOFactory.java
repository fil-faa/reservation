package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.dao.jpa.ReservationJPA;
import fr.emn.fil.reservation.model.dao.jpa.RessourceJPA;
import fr.emn.fil.reservation.model.dao.jpa.ResourceTypeJPA;
import fr.emn.fil.reservation.model.dao.jpa.UserJPA;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class DAOFactory {

    public static ReservationDAO reversationDAO() {
        return new ReservationJPA();
    }

    public static ResourceDAO ressourceDAO() {
        return new RessourceJPA();
    }

    public static UserDAO userDAO() {
        return new UserJPA();
    }

    public static ResourceTypeDAO resourceTypeDAO() {
        return new ResourceTypeJPA();
    }

}
