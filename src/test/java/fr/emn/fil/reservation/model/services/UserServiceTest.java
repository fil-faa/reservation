package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.UserDAO;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.GenericError;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alexandre on 21/10/2015.
 */

public class UserServiceTest extends EasyMockSupport {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private UserDAO userDAO;

    @TestSubject
    private final UserService userService = new UserService(userDAO);

    /** Nominal test, when the users satisfies all the rules
     * The user must be saved in the database, after verifications
     */
    @Test
    public void testCreate() {
        String firstName = "Alexandre";
        String mail = "a@f.fr";
        String lastName = "LEBRUN";
        String phone = "0522222222";
        String password = "test";
        String encryptedPassword = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        boolean admin = false;

        /** first the service must check if there is the same email in the database */
        EasyMock.expect(userDAO.byMail(mail)).andReturn(null);

        /** if the email is not present, we can save it
            The password must be encrypted  */
        User toSave = new User(firstName, lastName, mail, encryptedPassword, phone, admin);
        userDAO.save(toSave);
        EasyMock.expectLastCall().times(1);
        replayAll();
        try {
            User saved = userService.create(mail, password, firstName, lastName, phone);
            verifyAll();
            /** We assert that the password is correctly hashed in SHA-256 */
            assertEquals(saved.getPassword(), encryptedPassword);

        } catch(Exception e) {
            fail();
        }
    }

    /**
     * Exception case : an user already exists with this email in the database
     * The service must throw a <code>ModelError</code>
     */
    @Test
    public void testExistingMail() {
        String firstName = "Alexandre";
        String mail = "a@f.fr";
        String lastName = "LEBRUN";
        String phone = "0522222222";
        String password = "test";

        EasyMock.expect(userDAO.byMail(mail)).andReturn(new User(1L, firstName, lastName, mail, password, phone, true));
        replayAll();

        ModelError error = null;
        try {
            userService.create(mail, password, firstName, lastName, phone);
        } catch(ModelError e) {
            error = e;
        }
        verifyAll();
        assertNotNull(error);
    }

    /**
     * Method tested : login
     * Nominal test : the user is really in the database, and the password is correct
     */
    @Test
    public void testLoginOK() {
        String mail = "alexandre.lebrun@etudiant.mines-nantes.fr";
        String password = "mot de passe";
        String hashedPassword = "b9e50e0e8b504aa57a1bb6711ee832ee4ce9c641a1618b91833582382c709023";

        EasyMock.expect(userDAO.byMail(mail)).andReturn(new User(1L, "Alexandre", "LEBRUN", mail,
                hashedPassword, "0672566252", false));
        replayAll();

        User user;
        try {
            user = userService.connect(mail, password);
        } catch(ModelError e) {
            fail();
            user = null;
        }
        assertNotNull(user);
        assertNotEquals(user.getPassword(), password); // the password must be hashed

        verifyAll();
    }

    @Test
    public void testLoginIncorrectPassword() {
        String mail = "alexandre.lebrun@etudiant.mines-nantes.fr";
        String password = "wrong password";
        String hashedPassword = "b9e50e0e8b504aa57a1bb6711ee832ee4ce9c641a1618b91833582382c709023";

        EasyMock.expect(userDAO.byMail(mail)).andReturn(new User(1L, "Alexandre", "LEBRUN", mail,
                hashedPassword, "0672566252", false));
        replayAll();

        GenericError error;
        try {
            userService.connect(mail, password);
            error = null;
        } catch(ModelError e) {
            error = e;
        }
        assertNotNull(error);
    }

    /** Nominal test, when the users satisfies all the rules
     * The user must be saved in the database, after verifications
     */
    @Test
    public void testDelete() {
        String firstName = "Alexandre";
        String mail = "a@f.fr";
        String lastName = "LEBRUN";
        String phone = "0522222222";
        String password = "test";
        String encryptedPassword = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        boolean admin = false;

        /* Create the user */
        User toRemove = new User(1L,firstName, lastName, mail, encryptedPassword, phone, admin);
        /*Check if the user exists before remove it */
        EasyMock.expect(userDAO.byId(toRemove.getId())).andReturn(toRemove);
        userDAO.delete(toRemove);
        EasyMock.expectLastCall().once();

        replayAll();
        try {
            userService.delete(toRemove.getId());
            verifyAll();
        } catch(Exception e) {
            fail();
        }
    }
}
