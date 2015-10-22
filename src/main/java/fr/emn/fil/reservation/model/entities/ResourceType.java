package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "TYPE_RESSOURCE")
@NamedQueries({
        @NamedQuery(name = "resourceType.findAll", query = "SELECT r FROM ResourceType r")
})
public class ResourceType {

    @Id
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Resource> reservations;


    public ResourceType() {
    }

    public ResourceType(String nom) {
        this.name = nom;
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

    public void setName(String nom) {
        this.name = nom;
    }

    public List<Resource> getReservations() {
        return reservations;
    }

    public void setReservations(List<Resource> reservations) {
        this.reservations = reservations;
    }
}
