package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.CryptUtils;
import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.entities.User;
import static fr.emn.fil.reservation.CryptUtils.hash;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = DAOFactory.userDAO();
    }

    public User connect(String mail, String password) throws GenericError {
        User user = userDAO.byMail(mail);
        if(user == null || !hash(password).equals(user.getPassword()))
            throw new GenericError("Utilisateur non trouv?");

        return user;
    }

    public void delete(Long id) throws GenericError {
        User user = userDAO.byId(id);
        if(user == null)
            throw new GenericError("L'utilisateur que vous voulez supprimer n'existe pas");

        userDAO.delete(user);
    }

    public User create(String mail, String password, String firstName, String lastName, String phone) throws GenericError {
        String hashed = CryptUtils.hash(password);

        if(userDAO.byMail(mail) != null)
            throw new GenericError("Cette adresse email a d?j? ?t? prise");

        // We create the new user. It's not an admin (the admins are directly created in the database)
        User toCreate = new User(lastName, firstName, mail, hashed, phone, false);
        userDAO.save(toCreate);

        return toCreate;
    }

    public List<User> findAll() {
        return DAOFactory.userDAO().findAll();
    }

    public User byId(Long id) throws ModelError {
        User user = userDAO.byId(id);
        if(user == null) throw new ModelError("Utilisateur non trouv� pour l'identifiant donn�");
        return user;
    }

    public void save(User user) {
        userDAO.save(user);
    }
}