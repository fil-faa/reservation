package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.CryptUtils;
import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.entities.User;
import static fr.emn.fil.reservation.CryptUtils.hash;
import fr.emn.fil.reservation.model.exceptions.Error;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserService {

    public User connect(String mail, String password) throws Error {
        User user = DAOFactory.userDAO().byMail(mail);
        if(user == null || !hash(password).equals(user.getPassword()))
            throw new Error("Utilisateur non trouvé");

        return user;
    }

    public User create(String mail, String password, String firstName, String lastName, String phone) throws Error {
        String hashed = CryptUtils.hash(password);

        UserDAO dao = DAOFactory.userDAO();
        if(dao.byMail(mail) != null)
            throw new Error("Cette adresse email a déjà été prise");

        // We create the new user. It's not an admin (the admins are directly created in the database)
        User toCreate = new User(lastName, firstName, mail, hashed, phone, false);
        dao.save(toCreate);

        return toCreate;
    }

    public List<User> findAll() {
        return DAOFactory.userDAO().findAll();
    }


}
