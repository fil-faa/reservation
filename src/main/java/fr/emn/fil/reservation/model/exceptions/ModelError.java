package fr.emn.fil.reservation.model.exceptions;

/**
 * Exception thrown when a error related to the model and the constraints
 * is detected
 * @see GenericError
 * Created by Alexandre on 22/10/2015.
 */
public class ModelError extends GenericError {

    public ModelError(String label) {
        super(label);
    }

}
