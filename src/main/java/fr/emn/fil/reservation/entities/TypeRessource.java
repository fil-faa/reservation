package fr.emn.fil.reservation.entities;

import javax.persistence.*;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "typeRessource.findAll", query = "SELECT r FROM TypeRessource r")
})
public class TypeRessource {
    private int id;
    private String nom;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOM")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeRessource that = (TypeRessource) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }
}
