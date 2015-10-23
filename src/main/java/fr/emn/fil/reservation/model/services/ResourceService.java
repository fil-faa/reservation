package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.List;

/**
 * Created by xela on 21/10/15.
 */
public class ResourceService {

    private ResourceDAO resourceDAO;

    public ResourceService() {
        this.resourceDAO = DAOFactory.resourceDAO();
    }

    public List<Resource> findAll() {
        return  resourceDAO.findAll();
    }

    public Resource create(String name, User user, ResourceType resourceType, String place, String description) {
        Resource resource = new Resource(name, user, resourceType, description, place);
        resourceDAO.save(resource);
        return resource;
    }

    public void delete(Resource resource) {
        resourceDAO.delete(resource);
    }

    public Resource byId(Long id) throws ModelError {
        Resource resource = resourceDAO.byId(id);
        if(resource == null) throw new ModelError("Ressource non trouvée pour l'id donné");
        return resource;
    }

}
