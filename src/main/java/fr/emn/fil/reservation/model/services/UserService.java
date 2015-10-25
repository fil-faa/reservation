package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.CryptUtils;
import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.User;

import static fr.emn.fil.reservation.CryptUtils.hash;

import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = DAOFactory.userDAO();
    }

    public UserService(UserDAO dao) {
        this.userDAO = dao;
    }

    public User connect(String mail, String password) throws ModelError {
        User user = userDAO.byMail(mail);
        if (user == null || !hash(password).equals(user.getPassword()))
            throw new ModelError("Utilisateur non trouv?");

        return user;
    }

    public void delete(Long id) throws GenericError {
        User user = userDAO.byId(id);
        if (user == null)
            throw new GenericError("L'utilisateur que vous voulez supprimer n'existe pas");

        userDAO.delete(user);
    }

    public User create(@NotNull String mail, @NotNull String password,
                       @NotNull String firstName, @NotNull String lastName,
                       @NotNull String phone) throws ModelError {
        String hashed = CryptUtils.hash(password);

        if (userDAO.byMail(mail) != null)
            throw new ModelError("Cette adresse email a déjà été prise");

        // We create the new user. It's not an admin (the admins are directly created in the database)
        User toCreate = new User(firstName, lastName, mail, hashed, phone, false);
        userDAO.save(toCreate);

        return toCreate;
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public List<User> filter(String firstName, String lastName, String mail, String phone) {
        return userDAO.matching(firstName, lastName, mail, phone);
    }

    public User byId(Long id) throws ModelError {
        User user = userDAO.byId(id);
        if (user == null) throw new ModelError("Utilisateur non trouvé pour l'identifiant donné");
        return user;
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public boolean isUserDeletable(Long userId) {
        try {
            User user = byId(userId);

            // if user is last admin we cannot delete him
            List<User> admins = findAdmin();
            if (admins.size() == 1 && user.isAdmin()) {
                return false;
            }

            // if user is admin and have resources we must set those resource to another admin
            User admin = getFirstDifferentAdmin(admins, userId);
            ResourceService resourceService = new ResourceService();
            List<Resource> resources = resourceService.findByOwner(user);

            for (Resource resource : resources) {
                resource.setOwner(admin);
                resourceService.save(resource);
            }

            // finally we must delete the books made by this user
            ReservationService reservationService = new ReservationService();
            List<Reservation> reservations = reservationService.byUser(user);

            for (Reservation reservation : reservations) {
                reservationService.delete(reservation);
            }
            return true;
        } catch (ModelError modelError) {
            modelError.printStackTrace();
        }
        return false;

    }

    private User getFirstDifferentAdmin(List<User> admins, Long userId) {
        for (User admin : admins) {
            if (admin.getId() != userId) {
                return admin;
            }
        }
        return null;
    }

    public List<User> findAdmin() {
        return userDAO.findAdmin();
    }
}