package fr.emn.fil.reservation.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by arthur on 20/10/15.
 */
@Entity
public class Reservation {
    private int id;
    private Timestamp datedebut;
    private Timestamp datefin;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DATEDEBUT")
    public Timestamp getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }

    @Basic
    @Column(name = "DATEFIN")
    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (datedebut != null ? !datedebut.equals(that.datedebut) : that.datedebut != null) return false;
        if (datefin != null ? !datefin.equals(that.datefin) : that.datefin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (datedebut != null ? datedebut.hashCode() : 0);
        result = 31 * result + (datefin != null ? datefin.hashCode() : 0);
        return result;
    }
}
