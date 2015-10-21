package fr.emn.fil.reservation.dao;

import fr.emn.fil.reservation.entities.User;

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

}
