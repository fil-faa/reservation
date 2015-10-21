package fr.emn.fil.reservation.model.exceptions;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class ModelError extends Exception {

    private String label;

    public ModelError(String label) {
        super(label);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
