package br.com.ceslab.ceslab.resources.execpetion;

import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import br.com.ceslab.ceslab.services.exceptions.TokenInvalidException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //listen of all exception in the app
public class ResourceExceptionHandler {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> notFoundExceptionResponseEntity(ResourceNotFound e,  HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErros(MethodArgumentNotValidException e,  HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; //Significa que a entidade não pôde ser processada 422
        //StandardError error = new StandardError();
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addErros(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(UsernameNotFoundException e,  HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("User not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<StandardError> JwtTokenCreate(JWTCreationException e,  HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Erro while create token");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<StandardError> TokenExpired(TokenExpiredException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("The Token has expired");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<StandardError> TokenExpiredVerify(JWTDecodeException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("The Token has expired");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<StandardError> tokenInvalidException(TokenInvalidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Token invalid");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
