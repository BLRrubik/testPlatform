package ru.vibelab.tplatfom.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

}