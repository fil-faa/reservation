package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.ReservationDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by alexa on 25/10/2015.
 */
public class ReservationServiceTest extends EasyMockSupport {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private ReservationDAO reservationDAO;

    @TestSubject
    private final ReservationService reservationService = new ReservationService(reservationDAO);

    private final ResourceType type = new ResourceType(1L, "Matériel");

    private final User user = new User(1L, "Alexandre", "LEBRUN", "a@f.fr", "pwd", "0672566352", false);

    private final Resource resource = new Resource(1L, "name", user, type, "description", "place");


    /**
     * Nominal test
     * Firstly, the service must ensure that no rservation are during the selected period
     * Then the DAO must be called with a new reservation
     */
    @Test
    public void testCreate() {
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after

        EasyMock.expect(reservationDAO.during(resource, startDate, endDate))
                .andReturn(new ArrayList<Reservation>());

        reservationDAO.save(new Reservation(startDate, endDate, resource, user));
        EasyMock.expectLastCall();
        replayAll();

        try {
            Reservation created = reservationService.create(startDate, endDate, resource, user);
            assertNotNull(created);
        } catch(ModelError e) {
            fail();
        }
        verifyAll();
    }

    /**
     * If the end is before the start, a model exception must be thrown
     */
    @Test
    public void testCreateWrongOrder() {
        final Date endDate = new Date(new Date().getTime() + 1000);
        final Date startDate = new Date(endDate.getTime() + 1000);

        replayAll();
        ModelError error = null;
        try {
            reservationService.create(startDate, endDate, resource, user);
        } catch(ModelError e) {
            error = e;
        }
        assertNotNull(error);
        verifyAll();
    }

    /**
     * If the reservation is in the past, a model exception must be thrown
     */
    @Test
    public void testCreateBeforeToday() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);
        final Date startDate = calendar.getTime(); // the start date is yesterday
        final Date endDate = new Date(startDate.getTime() + 1000);

        replayAll();
        ModelError error = null;
        try {
            reservationService.create(startDate, endDate, resource, user);
        } catch(ModelError e) {
            error = e;
        }
        assertNotNull(error);
        verifyAll();
    }

    /**
     * A reservation already exists for the selected period
     * An exception must be thrown : ubiquity error
     */
    @Test
    public void testCreateReservationsDuring() {
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after

        List<Reservation> during = new ArrayList<>();
        during.add(new Reservation(new Date(startDate.getTime() - 1000),
                new Date(endDate.getTime() - 1000), resource, user));

        EasyMock.expect(reservationDAO.during(resource, startDate, endDate))
                .andReturn(during);

        replayAll();
        ModelError error = null;
        try {
            reservationService.create(startDate, endDate, resource, user);
        } catch(ModelError e) {
            error = e;
        }

        assertNotNull(error);
        verifyAll();
    }

    /**
     * Nominal test of cancel
     * A reservation is already created, and we want to cancel it
     */
    @Test
    public void testCancel() {
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after
        final Reservation toCancel = new Reservation(1L, startDate, endDate, resource, user);


        EasyMock.expect(reservationDAO.byId(toCancel.getId())).andReturn(toCancel);
        reservationDAO.delete(toCancel);
        EasyMock.expectLastCall();
        replayAll();

        try {
            reservationService.cancel(user, toCancel.getId());
        } catch(ModelError e) {
            fail();
        }
        verifyAll();
    }

    @Test
    public void testCancelWrongUser() {
        final User otherUser = new User(2L, "Faouzi", "CHIHEB", "f.chiheb@d.fr", "pwd", null, false);
        final Date startDate = new Date(new Date().getTime() + 1000); // the date is future to avoid a ModelError
        final Date endDate = new Date(startDate.getTime() + 1000);    // and end date just after
        final Reservation toCancel = new Reservation(1L, startDate, endDate, resource, otherUser);

        EasyMock.expect(reservationDAO.byId(toCancel.getId())).andReturn(toCancel);
        replayAll();

        ModelError error = null;
        try {
            reservationService.cancel(user, toCancel.getId());
        } catch(ModelError e) {
            error = e;
        }
        assertNotNull(error);
        verifyAll();
    }

}
