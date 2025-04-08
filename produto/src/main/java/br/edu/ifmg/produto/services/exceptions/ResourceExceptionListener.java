package br.edu.ifmg.produto.services.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

//evento que fica escutando o tempo todo e é ativado caso tiver algum erro do tipo "Resource not found"
@ControllerAdvice
public class ResourceExceptionListener {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFound exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND; //uma boa pratica é criar um enum com os erros ao invés de hardcoded
        StandartError error = new StandartError();
        error.setStatus(status.value());
        error.setMessage(exception.getMessage());
        error.setError("Resource not found");
        error.setTimeStamp(Instant.now());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
