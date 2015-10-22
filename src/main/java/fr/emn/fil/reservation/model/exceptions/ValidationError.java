package fr.emn.fil.reservation.model.exceptions;

/**
 * Created by Alexandre on 22/10/2015.
 */
public class ValidationError extends GenericError {

    public ValidationError(String label) {
        super(label);
    }

}
