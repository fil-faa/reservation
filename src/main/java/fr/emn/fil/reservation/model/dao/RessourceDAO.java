package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Ressource;

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
