package fr.emn.fil.reservation.dao;

import fr.emn.fil.reservation.entities.TypeRessource;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Alexandre on 20/10/2015.
 */
public interface TypeRessourceDAO {

    List<TypeRessource> findAll();

    void save(TypeRessource toSave);

    void update(TypeRessource toUpdate);

    void delete(TypeRessource toDelete);
}
