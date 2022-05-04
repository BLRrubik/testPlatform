package ru.vibelab.tplatfom.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.DTO.auth.AuthDTO;
import ru.vibelab.tplatfom.DTO.auth.RegistrationDTO;
import ru.vibelab.tplatfom.requests.auth.AuthRequest;
import ru.vibelab.tplatfom.requests.auth.RegistrationRequest;
import ru.vibelab.tplatfom.services.UserService;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
@Api(value = "auth", description = "Rest API for auth operations", tags = "Auth API")
public class AuthController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Регистрация пользователя")
    @PostMapping("/register")
    public RegistrationDTO registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @ApiOperation(value = "Авторизация пользователя")
    @PostMapping("/login")
    public AuthDTO auth(@RequestBody AuthRequest request) {
        return userService.authUser(request);
    }
}
