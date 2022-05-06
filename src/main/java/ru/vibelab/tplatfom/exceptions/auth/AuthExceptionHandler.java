package ru.vibelab.tplatfom.exceptions.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vibelab.tplatfom.DTO.ExceptionDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ExceptionDTO> userNotFound(UnauthorizedException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }
}
