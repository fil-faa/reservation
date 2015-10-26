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

    public ResourceService(ResourceDAO dao) {
        this.resourceDAO = dao;
    }

    public List<Resource> findAll() {
        return  resourceDAO.findAll();
    }

    public List<Resource> findByType(ResourceType type) {
        return resourceDAO.findByType(type);
    }

    public List<Resource> findByName(String name) {
        return resourceDAO.findByTypeAndName(null, name);
    }

    public List<Resource> findByTypeAndName(ResourceType type, String name) {
        return resourceDAO.findByTypeAndName(type, name);
    }

    public Resource create(String name, User user, ResourceType resourceType, String place, String description) {
        Resource resource = new Resource(name, user, resourceType, description, place);
        resourceDAO.save(resource);
        return resource;
    }

    public List<Resource> findAvailableResources(Date startDate, Date endDate, Long typeId, String resourceName) {
        List<Resource> available = resourceDAO.findAvailable(startDate, endDate);
        if(startDate == null && endDate==null) return available;

        // else, we filter by the type id
        Iterator<Resource> it = available.iterator();
        while(it.hasNext()) {
            Resource resource = it.next();
            boolean delete=false;
            if(typeId!=null)
                if(!resource.getType().getId().equals(typeId))
                    delete=true;
            if(resourceName != null)
                if(!resource.getName().toLowerCase().contains(resourceName.toLowerCase()))
                    delete=true;
            if(delete)
                it.remove();
        }

        return available;
    }

    public void delete(Resource resource) throws ModelError {
        if(!resource.hasReservationsOngoing())
            resourceDAO.delete(resource);
        else throw new ModelError("Impossible de supprimer la ressource : des r�servations futures de cette ressource sont programm�es");
    }

    public Resource byId(Long id) throws ModelError {
        if(id == null) throw new ModelError("Identifiant non fourni pour la ressource � chercher");
        Resource resource = resourceDAO.byId(id);
        if(resource == null) throw new ModelError("Ressource non trouv�e pour l'id donn�");
        return resource;
    }

    public void save(Resource resource) {
        resourceDAO.save(resource);
    }

    public List<Resource> findByOwner(User user) {
        return resourceDAO.findByOwner(user.getId());
    }
}
