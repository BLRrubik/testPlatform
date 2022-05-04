package ru.vibelab.tplatfom.exceptions.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vibelab.tplatfom.DTO.ExceptionDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class RoleExceptionHandler {
    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ExceptionDTO> userNotFound(RoleNotFoundException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
