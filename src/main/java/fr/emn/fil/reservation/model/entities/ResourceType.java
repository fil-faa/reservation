package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "RESOURCE_TYPE")
@NamedQueries({
        @NamedQuery(name = "resourceType.findAll", query = "SELECT r FROM ResourceType r")
})
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Resource> resources;

    public ResourceType() {
        this.resources = new ArrayList<>();
    }

    public ResourceType(String name) {
        this();
        this.name = name;
    }

    public ResourceType(Long id, String name) {
        this(name);
        this.id = id;
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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
