package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by arthur on 20/10/15.
 */

@NamedQueries({
        @NamedQuery(name = "reservation.findAll", query = "SELECT r FROM Reservation r")
})
@Table(name = "RESERVATION")
@Entity
public class Reservation {

    @Id
    @Column(name = "ID")
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
}
