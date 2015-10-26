package fr.emn.fil.reservation.model.dao;

import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;

import java.util.Date;
import java.util.List;

public interface ResourceDAO {

    List<Resource> findAll();

    List<Resource> findByType(ResourceType type);

    List<Resource> findByTypeAndName(ResourceType type, String name);

    void save(Resource toSave);

    void update(Resource toUpdate);

    void delete(Resource toDelete);

    Resource byId(Long resourceId);

    List<Resource> findAvailable(Date startDate, Date endDate);

    List<Resource> findByOwner(Long id);
}
