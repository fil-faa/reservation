package fr.emn.fil.reservation.model.services;

import fr.emn.fil.reservation.model.dao.DAOFactory;
import fr.emn.fil.reservation.model.entities.Resource;

import java.util.List;

/**
 * Created by xela on 21/10/15.
 */
public class ResourceService {

    public List<Resource> findAll() {
        return  DAOFactory.ressourceDAO().findAll();
    }

}
