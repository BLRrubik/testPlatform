package ru.vibelab.tplatfom.exceptions.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vibelab.tplatfom.DTO.ExceptionDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class TestExceptionHandler {
    @ExceptionHandler(value = TestNotFoundException.class)
    public ResponseEntity<ExceptionDTO> userNotFound(TestNotFoundException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TestFinishedException.class)
    public ResponseEntity<ExceptionDTO> testFinished(TestFinishedException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TestNotStartedException.class)
    public ResponseEntity<ExceptionDTO> testNotStarted(TestNotStartedException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
