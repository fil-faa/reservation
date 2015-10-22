package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.entities.User;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserJPA extends AbstractJpaDAO<User> implements UserDAO {

    public UserJPA() {
        super(User.class);
    }

    public List<User> findAll() {
        Query q = jpaManager.getEm().createNamedQuery("user.findAll");
        List<User> users = q.getResultList();
        return users;
    }

    public User byMail(String mail) {
        Query q = jpaManager.getEm().createNamedQuery("user.byMail");
        q.setParameter("mail", mail);
        List<User> users = q.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    public User byId(Long userId) {
        return super.byId(userId);
    }
}
