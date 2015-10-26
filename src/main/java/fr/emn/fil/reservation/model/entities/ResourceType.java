package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Different types of resources exists : they can be added by the user within the application
 * Created by arthur on 20/10/15.
 */
@Entity
@Table(name = "RESOURCE_TYPE")
@NamedQueries({
        @NamedQuery(name = "resourceType.findAll", query = "SELECT r FROM ResourceType r"),
        @NamedQuery(name = "resourceType.byNameLike", query = "SELECT r FROM ResourceType r WHERE UPPER(r.name) LIKE :name")
})
public class ResourceType {

    /**----------------------------------------------
     *               ENTITY FIELDS
     *---------------------------------------------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Resource> resources;



    /**----------------------------------------------
     *                CONSTRUCTORS
     *---------------------------------------------*/

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



    /**----------------------------------------------
     *             GETTERS AND SETTERS
     *---------------------------------------------*/

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



    /**----------------------------------------------
     *              EQUALS AND HASHCODE
     *---------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceType that = (ResourceType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(resources != null ? !resources.equals(that.resources) : that.resources != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        return result;
    }
}
