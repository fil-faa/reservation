package fr.emn.fil.reservation.services;

import fr.emn.fil.reservation.entities.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alexandre on 21/10/2015.
 */

public class UserServiceTest {


    @Test
    public void testCreate() {
        String name = "Alexandre";
        String mail = "a@f.fr";
        String surname = "LEBRUN";
        String phone = "0522222222";
        String password = "test";

        UserService service  = new UserService();

        User user = service.create(mail, password, name, surname, phone);

        assertNotNull(user);
        assertEquals(user.getPassword(), "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
    }
}
