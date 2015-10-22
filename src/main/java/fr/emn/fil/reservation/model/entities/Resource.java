package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "RESSOURCE")
@NamedQueries({
        @NamedQuery(name = "resource.findAll", query = "SELECT r FROM Resource r")
})
public class Resource {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "NOM")
    private String name;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "PLACE")
    private String place;

    @OneToMany(mappedBy = "resource")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private ResourceType type;

    public Resource() {
    }

    public Resource(String name, String description, String place) {
        this.name = name;
        this.description = description;
        this.place = place;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}