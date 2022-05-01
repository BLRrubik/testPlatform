package ru.vibelab.tplatfom.DTO;

import java.time.LocalDateTime;

public class ExceptionDTO {
    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionDTO(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}