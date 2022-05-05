package ru.vibelab.tplatfom.exceptions.auth;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
