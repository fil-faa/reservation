package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ResourceTypeDAO;
import fr.emn.fil.reservation.model.entities.ResourceType;

import java.util.List;

/**
 * Created by Alexandre on 22/10/2015.
 */
public class ResourceTypeService {

    private ResourceTypeDAO resourceTypeDAO;

    public ResourceTypeService() {
        this.resourceTypeDAO = DAOFactory.resourceTypeDAO();
    }

    public List<ResourceType> findAll() {
        return resourceTypeDAO.findAll();
    }

    public ResourceType create(String name) {
        ResourceType type = new ResourceType(name);
        resourceTypeDAO.save(type);
        return type;
    }

}
