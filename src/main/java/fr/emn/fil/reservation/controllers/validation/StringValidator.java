package fr.emn.fil.reservation.controllers.validation;

import fr.emn.fil.reservation.model.exceptions.ValidationError;

/**
 * This class helps to validate several strings by giving the wanted pattern
 * It follows a Builder like design pattern : you must instantiate an object.
 * Then you can validate it
 * Created by Alexandre on 22/10/2015.
 */
public class StringValidator {

    /**
     * Field name
     */
    private String label;

    /**
     * String which will be validated
     */
    private String toValidate;

    public StringValidator(String toValidate, String label) {
        this.toValidate = toValidate;
        this.label = label;
    }

    public StringValidator notEmpty() throws ValidationError {
        if(toValidate == null || toValidate.length() == 0)
            throw new ValidationError("Veuillez remplir le champ \"" + label + "\"");
        return this;
    }

    public StringValidator minLength(int length) throws ValidationError {
        if(toValidate == null) return this;
        if(toValidate.length() < length)
            throw new ValidationError("Le champ \"" + label + "\" doit contenir au moins " + length + " caractères.");
        return this;
    }

    public StringValidator maxLength(int length)throws ValidationError {
        if(toValidate == null) return this;
        if(toValidate.length() > length)
            throw new ValidationError("Le champ \"" + label + "\" ne peut pas contenir plus de " + length + " caractères.");
        return this;
    }

    public StringValidator mustContain(String... elements) throws ValidationError {
        if(toValidate == null) return this;
        for(String element : elements) {
            if(!toValidate.contains(element))
                throw new ValidationError("Le champ \"" + label + "\" doit contenir \"" + element + "\"");
        }
        return this;
    }

    public StringValidator mustBeNumeric() throws ValidationError {
        if(toValidate == null) return this;
        try {
            Long parsed = Long.parseLong(toValidate);
        } catch(NumberFormatException e) {
            throw new ValidationError("Le champ \"" + label + "\" ne doit contenir que des chiffres.");
        }
        return this;
    }

    public String get() {
        return this.label;
    }


}
