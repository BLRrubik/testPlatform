package ru.vibelab.tplatfom.exceptions.test;

public class TestNotStartedException extends RuntimeException {
    public TestNotStartedException(Long id) {
        super("Test " + id + " not started yet");
    }
}
