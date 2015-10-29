package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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


    private final ResourceType type1 = new ResourceType(1L, "Test 1");
    private final ResourceType type2 = new ResourceType(0L, "Test 2");


    @Test
    public void testFindAvailableResources() {
        // three reservations in the database
        final Resource res1 = new Resource(1L, "MATCH1 1", user, type1, "description 3", "place 3");
        final Resource res2 = new Resource(1L, "MATCH1 2", user, type2, "description 2", "place 2");
        final Resource res3 = new Resource(1L, "MATCH1 3", user, type2, "description 1", "place 1");
        final Resource res4 = new Resource(1L, "test 1", user, type1, "description 1", "place 1");
        final Resource res5 = new Resource(1L, "test 1", user, type2, "description 1", "place 1");

        // one is booked
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after

        /********************
         *  What we expect
         ********************/
        List<Resource> result = new ArrayList<>();
        result.add(res1);
        result.add(res2);
        result.add(res3);
        result.add(res4);
        result.add(res5);

        EasyMock.expect(resourceDAO.findAvailable(startDate, endDate)).andReturn(result);
        replayAll();

        /********************
         *  Call of the service
         ********************/
        List<Resource> found = resourceService.findAvailableResources(startDate, endDate, type2.getId(), "MATCH1");

        /********************
         *  Check
         ********************/

        assertThat(found, is(Arrays.asList(res2, res3)));
        verifyAll();

    }

}
