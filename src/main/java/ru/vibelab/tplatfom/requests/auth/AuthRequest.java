package ru.vibelab.tplatfom.requests.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}