package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.User;

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
}
