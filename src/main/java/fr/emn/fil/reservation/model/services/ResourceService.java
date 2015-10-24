package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ResourceDAO;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.entities.User;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.Date;
import java.util.Iterator;
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

    public List<Resource> findByType(ResourceType type) {
        return resourceDAO.findByType(type);
    }

    public List<Resource> findByName(String name) {
        return filter(resourceDAO.findAll(), name);
    }

    public List<Resource> findbyTypeAndName(ResourceType type, String name) {
        return filter(resourceDAO.findByType(type), name);
    }

    private List<Resource> filter(List<Resource> resources, String name) {
        Iterator<Resource> it = resources.iterator();
        while(it.hasNext()) {
            Resource resource = it.next();
            if(!resource.getName().toLowerCase().contains(name.toLowerCase()))
                it.remove();
        }
        return resources;
    }


    public Resource create(String name, User user, ResourceType resourceType, String place, String description) {
        Resource resource = new Resource(name, user, resourceType, description, place);
        resourceDAO.save(resource);
        return resource;
    }

    public List<Resource> findAvailableResources(Date startDate, Date endDate) {
        List<Resource> available = resourceDAO.findAvailable(startDate, endDate);
        // double check if the resource is really available ?
        return available;
    }

    public void delete(Resource resource) {
        resourceDAO.delete(resource);
    }

    public Resource byId(Long id) throws ModelError {
        if(id == null) throw new ModelError("Identifiant non fourni pour la ressource à chercher");
        Resource resource = resourceDAO.byId(id);
        if(resource == null) throw new ModelError("Ressource non trouvée pour l'id donné");
        return resource;
    }

}
