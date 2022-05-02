package ru.vibelab.tplatfom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vibelab.tplatfom.DTO.ExceptionDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class NumberFormatExceptionHandler {
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ExceptionDTO> wrongNumberFormat(NumberFormatException e) {
        return new ResponseEntity<>(
                new ExceptionDTO("Wrong number format", LocalDateTime.now()), HttpStatus.BAD_REQUEST
        );
    }
}
