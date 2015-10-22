package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.ResourceType;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface TypeRessourceDAO {

    List<RessourceType> findAll();

    void save(RessourceType toSave);

    void update(RessourceType toUpdate);

    void delete(RessourceType toDelete);
}
