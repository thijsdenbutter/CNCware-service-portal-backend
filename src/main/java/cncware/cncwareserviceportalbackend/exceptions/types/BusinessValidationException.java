package cncware.cncwareserviceportalbackend.exceptions.types;

public class BusinessValidationException extends RuntimeException {
    public BusinessValidationException(String message) {
        super(message);
    }
}
