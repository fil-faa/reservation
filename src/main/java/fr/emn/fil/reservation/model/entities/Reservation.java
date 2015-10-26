package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * A reservation links a resource to user during a specific period
 * When a resource is booked, it's unavailable for another booking at the same time
 * Created by arthur on 20/10/15.
 */
@SuppressWarnings("ALL")
@NamedQueries({
        @NamedQuery(name = "reservation.findAll", query = "SELECT r FROM Reservation r"),
        @NamedQuery(name = "reservation.byUser", query = "SELECT r FROM Reservation r WHERE r.user = :user"),
        @NamedQuery(name = "reservation.during", query = "SELECT r FROM Reservation r WHERE r.resource = :resource " +
                "AND r.start < :endDate AND r.end > :startDate"),
        @NamedQuery(name = "reservation.findAllDuring",  query = "SELECT r FROM Reservation r WHERE  " +
                " r.start < :endDate AND r.end > :startDate"),

        })
@Table(name = "RESERVATION")
@Entity
public class Reservation {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    // TODO granularity of the dates ?
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "START")
    private Date start;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "END")
    private Date end;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID")
    private Resource resource;

    public Reservation() {
    }

    public Reservation(Date start, Date end, Resource resource, User user) {
        this.start = start;
        this.end = end;
        this.resource = resource;
        this.user = user;
    }

    public Reservation(Long id, Date start, Date end, Resource resource, User user) {
        this(start, end, resource, user);
        this.id = id;
    }

    public boolean isOngoing() {
        Date currentDate = new Date();
        return this.end.getTime() > currentDate.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User type) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return !(resource != null ? !resource.equals(that.resource) : that.resource != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        return result;
    }
}
