package fr.emn.fil.reservation.model.dao.jpa;

import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.dao.jpa.JPAFilter.FilterType;
import fr.emn.fil.reservation.model.entities.User;

import javax.persistence.Query;
import java.util.List;

/**
 * JPA implementation of the user DAO
 * @see UserDAO
 */
public class UserJPA extends AbstractJpaDAO<User,Long> implements UserDAO {

    public UserJPA() {
        super(User.class);
    }

    public List<User> findAll() {
        Query q = JPAManager.getEm().createNamedQuery("user.findAll");
        return q.getResultList();
    }

    public User byMail(String mail) {
        Query q = JPAManager.getEm().createNamedQuery("user.byMail");
        q.setParameter("mail", mail);
        List<User> users = q.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public List<User> findAdmin() {
        String query = "SELECT usr FROM User usr WHERE usr.admin = '1'";
        Query q = JPAManager.getEm().createQuery(query);

        return q.getResultList();
    }

    /**
     * Dynamic query handling cumulative filters on users
     * @param firstName filter by first name (nullable)
     * @param lastName filter by last naeme (nullable)
     * @param mail filter by mail address (nullable)
     * @param phone filter by phone number (nullable)
     * @return
     */
    @Override
    public List<User> matching(String firstName, String lastName, String mail, String phone) {
        String query = JPAFilter.create(FilterType.STRING, "firstName", firstName)
                .add(FilterType.STRING, "lastName", lastName)
                .add(FilterType.STRING, "mail", mail)
                .add(FilterType.STRING, "telephone", phone)
                .getQuery("User");
        Query q = JPAManager.getEm().createQuery(query);
        return q.getResultList();
    }
}
