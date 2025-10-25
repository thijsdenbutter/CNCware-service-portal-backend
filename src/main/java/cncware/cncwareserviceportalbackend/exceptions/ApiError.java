package cncware.cncwareserviceportalbackend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public ApiError (HttpStatus status, String message, String path){
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
