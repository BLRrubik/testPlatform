package ru.vibelab.tplatfom.exceptions.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionNotFoundException extends ResponseStatusException {
    public QuestionNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "No question with id " + id);
    }
}
