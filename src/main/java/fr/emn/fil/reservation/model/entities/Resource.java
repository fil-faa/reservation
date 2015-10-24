package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "RESSOURCE")
@NamedQueries({
        @NamedQuery(name = "resource.findAll", query = "SELECT r FROM Resource r"),
        @NamedQuery(name = "resource.byType", query = "SELECT r FROM Resource r WHERE r.type = :type"),
        @NamedQuery(name = "resource.findAvailable", query ="SELECT r FROM Resource r " +
                "WHERE NOT r IN (SELECT res.resource FROM Reservation res WHERE res.resource = r AND res.start < :endDate AND res.end > :startDate)")
})
public class Resource {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "NOM", length = 255)
    private String name;

    @Basic
    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Basic
    @Column(name = "PLACE", length = 100)
    private String place;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @JoinColumn(name = "OWNER_ID")
    @ManyToOne
    private User owner;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private ResourceType type;

    public Resource() {
        this.reservations = new ArrayList<Reservation>();
    }

    public Resource(String name, User owner, ResourceType type, String description, String place) {
        this.name = name;
        this.type = type;
        this.reservations = new ArrayList<Reservation>();
        this.owner = owner;
        this.description = description;
        this.place = place;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
