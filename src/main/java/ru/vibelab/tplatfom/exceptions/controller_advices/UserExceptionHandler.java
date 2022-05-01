package ru.vibelab.tplatfom.exceptions.controller_advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vibelab.tplatfom.DTO.ExceptionDTO;
import ru.vibelab.tplatfom.exceptions.users.UserNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
