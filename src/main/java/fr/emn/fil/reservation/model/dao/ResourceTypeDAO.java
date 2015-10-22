package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.ResourceType;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface ResourceTypeDAO {

    List<ResourceType> findAll();

    void save(ResourceType toSave);

    void update(ResourceType toUpdate);

    void delete(ResourceType toDelete);
}
