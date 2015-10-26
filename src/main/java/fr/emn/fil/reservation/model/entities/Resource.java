package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A resource an object that can be booked.
 * It has a name, a description, and a place
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "RESOURCE")
@NamedQueries({
        @NamedQuery(name = "resource.findAll", query = "SELECT r FROM Resource r"),
        @NamedQuery(name = "resource.byType", query = "SELECT r FROM Resource r WHERE r.type = :type"),
        @NamedQuery(name = "resource.findAvailable", query ="SELECT r FROM Resource r " +
                "WHERE NOT r IN (SELECT res.resource FROM Reservation res WHERE res.resource = r AND res.start < :endDate AND res.end > :startDate)")
})
public class Resource {


    /**----------------------------------------------
     *               ENTITY FIELDS
     *---------------------------------------------*/


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 255)
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



    /**----------------------------------------------
     *                CONSTRUCTORS
     *---------------------------------------------*/

    public Resource() {
        this.reservations = new ArrayList<Reservation>();
    }

    public Resource(String name, User owner, ResourceType type, String description, String place) {
        this();
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.description = description;
        this.place = place;
    }

    public Resource(Long id, String name, User owner, ResourceType type, String description, String place) {
        this(name, owner, type, description, place);
        this.id = id;
    }


    /**----------------------------------------------
     *                  METHODS
     *---------------------------------------------*/

    public boolean hasReservationsOngoing() {
        for(Reservation r : this.reservations) {
            if(r.isOngoing())
                return true;
        }
        return false;
    }



    /**----------------------------------------------
     *             GETTERS AND SETTERS
     *---------------------------------------------*/

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



    /**----------------------------------------------
     *              EQUALS AND HASHCODE
     *---------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;
        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        if (description != null ? !description.equals(resource.description) : resource.description != null)
            return false;
        if (place != null ? !place.equals(resource.place) : resource.place != null) return false;
        if (reservations != null ? !reservations.equals(resource.reservations) : resource.reservations != null)
            return false;
        if (owner != null ? !owner.equals(resource.owner) : resource.owner != null) return false;
        return !(type != null ? !type.equals(resource.type) : resource.type != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (reservations != null ? reservations.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
