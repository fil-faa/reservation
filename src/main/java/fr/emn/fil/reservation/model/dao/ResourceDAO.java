package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Resource;

import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface ResourceDAO {

    List<Resource> findAll();

    void save(Resource toSave);

    void update(Resource toUpdate);

    void delete(Resource toDelete);

}
