package fr.emn.fil.reservation.model;

import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;

/**
 * Created by alexa on 24/10/2015.
 */
public class UserManager {

    public static ThreadLocal<User> users = new ThreadLocal<>();

    public static User getCurrentUser() throws ModelError {
        User user = users.get();
        if(user == null) {
            throw new ModelError("L'utilisateur courant n'a pas été trouvé");
        }
        return user;
    }

}
