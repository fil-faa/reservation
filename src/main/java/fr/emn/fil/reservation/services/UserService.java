package fr.emn.fil.reservation.services;

import fr.emn.fil.reservation.CryptUtils;
import fr.emn.fil.reservation.dao.DAOFactory;
import fr.emn.fil.reservation.entities.User;
import static fr.emn.fil.reservation.CryptUtils.encrypt;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserService {

    public User connect(String mail, String password) {
        User user = DAOFactory.userDAO().byMail(mail);
        if(user == null || !encrypt(password).equals(user.getPassword()))
            return null;

        return user;
    }
}
