package fr.emn.fil.reservation.entities;

import javax.persistence.*;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "ressource.findAll", query = "SELECT r FROM Ressource r")
})
public class Ressource {
    private String nom;
    private String description;
    private String localisation;
    private int id;

    @Basic
    @Column(name = "NOM")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "LOCALISATION")
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ressource ressource = (Ressource) o;

        if (id != ressource.id) return false;
        if (nom != null ? !nom.equals(ressource.nom) : ressource.nom != null) return false;
        if (description != null ? !description.equals(ressource.description) : ressource.description != null)
            return false;
        if (localisation != null ? !localisation.equals(ressource.localisation) : ressource.localisation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (localisation != null ? localisation.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
