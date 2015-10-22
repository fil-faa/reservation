package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;

import java.util.List;

public interface ResourceDAO {

    List<Resource> findAll();

    void save(Resource toSave);

    void update(Resource toUpdate);

    void delete(Resource toDelete);

    Resource byId(Long resourceId);

}
