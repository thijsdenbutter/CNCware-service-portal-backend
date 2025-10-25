package cncware.cncwareserviceportalbackend.exceptions.handlers;

import cncware.cncwareserviceportalbackend.exceptions.ApiError;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFoundHandler(ResourceNotFoundException exception, HttpServletRequest request){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> validationHandler(MethodArgumentNotValidException exception, HttpServletRequest request){
        String errorMessage = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, errorMessage, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
