package ru.vibelab.tplatfom.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vibelab.tplatfom.DTO.auth.AuthDTO;
import ru.vibelab.tplatfom.DTO.auth.RegistrationDTO;
import ru.vibelab.tplatfom.requests.RegistrationRequest;
import ru.vibelab.tplatfom.services.AuthService;
import ru.vibelab.tplatfom.services.RegistrationService;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    public RegistrationService registrationService;


    @PostMapping("/api/auth/login")
    public AuthDTO token(Authentication authentication) {
        return authService.authUser(authentication);
    }

    @GetMapping("/api/auth/checkjwt")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String hello(Authentication authentication) {
        return "Jwt token is good for username " + authentication.getName();
    }




    @ApiOperation(value = "Регистрация пользователя")
    @PostMapping("/api/auth/register")
    public RegistrationDTO registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {

        return registrationService.registerUser(registrationRequest);
    }
}
