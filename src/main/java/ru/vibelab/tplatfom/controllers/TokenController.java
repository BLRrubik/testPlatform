package ru.vibelab.tplatfom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vibelab.tplatfom.DTO.UserAuthDTO;
import ru.vibelab.tplatfom.services.AuthService;

@RestController
public class TokenController {

    @Autowired
    private AuthService authService;


    @PostMapping("/api/auth/login")
    public UserAuthDTO token(Authentication authentication) {
        return authService.authUser(authentication);
    }

}
