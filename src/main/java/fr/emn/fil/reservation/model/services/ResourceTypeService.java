package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.dao.ResourceTypeDAO;
import fr.emn.fil.reservation.model.entities.Reservation;
import fr.emn.fil.reservation.model.entities.Resource;
import fr.emn.fil.reservation.model.entities.ResourceType;
import fr.emn.fil.reservation.model.exceptions.ModelError;

import java.util.Date;
import java.util.List;

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

    public void delete(Long id) throws ModelError {
        ResourceType type = resourceTypeDAO.byId(id);
        if(type == null) throw new ModelError("Le type que vous voulez supprimer n'existe pas");

        // If there is a reservation which is not finished yet, we refuse to delete the type
        for(Resource resource : type.getResources()) {
            for(Reservation reservation : resource.getReservations())
                if(new Date().compareTo(reservation.getEnd()) > 0)
                    throw new ModelError("Suppression impossible : une réservation " +
                            "liée au type que vous voulez supprimée n'est pas encore terminée");
        }

        type.setResources(null);
        resourceTypeDAO.save(type);
        resourceTypeDAO.delete(type);
    }

}
