package fr.emn.fil.reservation.services;

import fr.emn.fil.reservation.CryptUtils;
import fr.emn.fil.reservation.dao.DAOFactory;
import fr.emn.fil.reservation.dao.UserDAO;
import fr.emn.fil.reservation.entities.User;
import static fr.emn.fil.reservation.CryptUtils.hash;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserService {

    public User connect(String mail, String password) {
        User user = DAOFactory.userDAO().byMail(mail);
        if(user == null || !hash(password).equals(user.getPassword()))
            return null;

        return user;
    }

    public User create(String mail, String password, String firstName, String lastName, String phone) {
        String hashed = CryptUtils.hash(password);

        UserDAO dao = DAOFactory.userDAO();
        if(dao.byMail(mail) != null)
            return null;

        // We create the new user. It's not an admin (the admins are directly created in the database)
        User toCreate = new User(lastName, firstName, mail, hashed, phone, false);
        dao.save(toCreate);

        return toCreate;
    }


}
