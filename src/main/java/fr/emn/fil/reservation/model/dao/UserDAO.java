package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.dao.jpa.JPAManager;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.User;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface UserDAO {

    List<User> findAll();

    void save(User toSave);

    void update(User toUpdate);

    void delete(User toDelete);

    User byMail(String mail);

    User byId(Long userId);

    List<User> findAdmin();

    List<User> matching(String firstName, String lastName, String mail, String phone);

}
