package ru.vibelab.tplatfom.requests.auth;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {

    @NotEmpty
    @NotNull(message = "username required")
    private String username;

    @NotEmpty
    @NotNull(message = "password required")
    private String password;

}
