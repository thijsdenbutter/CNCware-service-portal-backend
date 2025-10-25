package cncware.cncwareserviceportalbackend.exceptions.handlers;

import cncware.cncwareserviceportalbackend.exceptions.ApiError;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFoundHandler(ResourceNotFoundException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
