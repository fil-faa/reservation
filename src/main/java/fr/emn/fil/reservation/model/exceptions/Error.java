package fr.emn.fil.reservation.model.exceptions;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class Error extends Exception {

    private String name;

    public Error(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
