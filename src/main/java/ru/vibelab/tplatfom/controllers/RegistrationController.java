package ru.vibelab.tplatfom.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vibelab.tplatfom.DTO.UserRegistrationDTO;
import ru.vibelab.tplatfom.requests.RegistrationRequest;
import ru.vibelab.tplatfom.services.RegistrationService;

import javax.validation.Valid;


@RestController
public class RegistrationController {
    @Autowired
    public RegistrationService registrationService;

    @ApiOperation(value = "Регистрация пользователя")
    @PostMapping("/api/auth/register")
    public UserRegistrationDTO registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {

        return registrationService.registerUser(registrationRequest);
    }

}
