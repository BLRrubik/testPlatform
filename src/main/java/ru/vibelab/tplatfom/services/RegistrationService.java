package ru.vibelab.tplatfom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.auth.RegistrationDTO;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.domain.UserRole;
import ru.vibelab.tplatfom.repos.RoleRepo;
import ru.vibelab.tplatfom.repos.UserRoleRepo;
import ru.vibelab.tplatfom.requests.RegistrationRequest;

import javax.validation.Valid;

@Service
public class RegistrationService {
    @Autowired
    public RoleRepo userRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public UserRoleRepo userRoleRepo;
    public RegistrationDTO registerUser(@Valid RegistrationRequest registrationRequest) {

        User user = userRepo.findUserByUsername(registrationRequest.getUsername());

        if (user != null) {
            return new RegistrationDTO("Username already taken");
        }
        User createUser = new User();
        createUser.setUsername(registrationRequest.getUsername());
        createUser.setPassword(registrationRequest.getPassword());
        UserRole userRole = new UserRole();

        createUser = userRepo.save(createUser);
        userRole.setUser_id(createUser.getId());
        userRole.setRole_id(1L); //Student
        System.out.println(createUser);
        userRoleRepo.save(userRole);
        return new RegistrationDTO("OK");
    }
}
