package cncware.cncwareserviceportalbackend.exceptions.types;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("The requested resource could not be found.");
    }

    public ResourceNotFoundException (String message){
        super(message);
    }
}
