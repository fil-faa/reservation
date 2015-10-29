package fr.emn.fil.reservation.model.exceptions;

/**
 * This exception is thrown when a field given by the user does not match
 * requirements (length, nullability, pattern)
 * @see GenericError
 * * Created by Alexandre on 22/10/2015.
 */
public class ValidationError extends GenericError {

    public ValidationError(String label) {
        super(label);
    }

}
