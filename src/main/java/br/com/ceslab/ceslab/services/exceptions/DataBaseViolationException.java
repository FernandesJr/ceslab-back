package br.com.ceslab.ceslab.services.exceptions;

public class DataBaseViolationException extends RuntimeException{
    public DataBaseViolationException(String message) {
        super(message);
    }
}
