package ru.vibelab.tplatfom.exceptions.test;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(Long id) {
        super("No test with id " + id);
    }
}
