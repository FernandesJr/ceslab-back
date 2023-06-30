package br.com.ceslab.ceslab.resources.execpetion;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){}

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErros(String field, String message){
        errors.add(new FieldMessage(field, message));
    }
}
