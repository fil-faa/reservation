package fr.emn.fil.reservation.dao;

import fr.emn.fil.reservation.entities.Ressource;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface RessourceDAO {

    List<Ressource> findAll();

    void save(Ressource toSave);

    void update(Ressource toUpdate);

    void delete(Ressource toDelete);

}
