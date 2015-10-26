package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import org.easymock.*;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;

/**
 * Created by Alexandre on 26/10/2015.
 */
public class ResourceServiceTest extends EasyMockSupport {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private ResourceDAO resourceDAO;

    @TestSubject
    private ResourceService resourceService = new ResourceService(resourceDAO);

    /**
     * Some test data...
     */
    private final User user = new User(1L, "Alexandre", "LEBRUN", "alexandre.lebrun@etudiant.mines-nantes.fr"
            , "password", null, false);


    private final ResourceType type = new ResourceType(1L, "Test");

    @Test
    public void testFindAvailableResources() {
        // three reservations in the database
        final Resource res1 = new Resource(1L, "test 1", user, type, "description 1", "place 1");
        final Resource res2 = new Resource(1L, "test 2", user, type, "description 2", "place 2");
        final Resource res3 = new Resource(1L, "test 3", user, type, "description 3", "place 3");

        // one is booked
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after
        final Reservation reservation = new Reservation(startDate, endDate, res1, user);

        /********************
         *  What we expect
         ********************/

        List<Resource> result = new ArrayList<>();
        result.add(res1);
        result.add(res2);
        result.add(res3);

        EasyMock.expect(resourceDAO.findAvailable(startDate, endDate)).andReturn(result);

        /********************
         *  Call of the service
         ********************/

        List<Resource> found = resourceService.findAvailableResources(startDate, endDate, null, null); // no filter

        assertThat(found, containsInAnyOrder(res2, res3));
        assertThat(found, not(contains(res1)));



    }

}
