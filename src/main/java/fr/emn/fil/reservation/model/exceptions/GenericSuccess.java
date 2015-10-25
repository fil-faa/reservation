package fr.emn.fil.reservation.model.exceptions;

/**
 * Created by alexa on 25/10/2015.
 */

/**
 * Notifies the operations which have succeed
 * It's not an exception, although it could be thrown
 */
public class GenericSuccess {

    private String label;

    public GenericSuccess(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
