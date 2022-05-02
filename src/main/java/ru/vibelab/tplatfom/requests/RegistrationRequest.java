package ru.vibelab.tplatfom.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
