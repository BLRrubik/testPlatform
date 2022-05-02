package ru.vibelab.tplatfom.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("No user with id " + id);
    }
}
