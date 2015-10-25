package fr.emn.fil.reservation.controllers.validation;

import fr.emn.fil.reservation.model.exceptions.ModelError;
import fr.emn.fil.reservation.model.exceptions.ValidationError;

/**
 * Created by Alexandre on 25/10/2015.
 */
public class LongValidator {

    private String label;

    private Long toValidate;

    public LongValidator(String label) {
        this.label = label;
    }

    public LongValidator parse(String toParse) throws ModelError {
        try {
            if (toParse == null) throw new NumberFormatException();
            toValidate = Long.parseLong(toParse);
        } catch(NumberFormatException e) {
            throw new ModelError("Erreur de récupération de l'identifiant '" + label + "'");
        }
        return this;
    }

    public LongValidator minValue(Long value) throws ValidationError {
        if(toValidate < value) throw new ValidationError("Erreur de valiation : '" + label + "' doit être supérieur à " + value);
        return this;
    }

    public LongValidator maxValue(Long value) throws ValidationError {
        if(toValidate > value) throw new ValidationError("Erreur de valiation : '" + label + "' doit être supérieur à " + value);
        return this;
    }

    public Long get() {
        return this.toValidate;
    }
}
