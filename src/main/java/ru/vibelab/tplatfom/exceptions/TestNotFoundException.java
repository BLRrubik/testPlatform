package ru.vibelab.tplatfom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TestNotFoundException extends ResponseStatusException {
    public TestNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "No test with id " + id);
    }
}
