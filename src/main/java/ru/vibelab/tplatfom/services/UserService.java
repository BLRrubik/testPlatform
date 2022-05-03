package ru.vibelab.tplatfom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.auth.AuthDTO;
import ru.vibelab.tplatfom.DTO.auth.RegistrationDTO;
import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.user.UserDTO;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;
import ru.vibelab.tplatfom.domain.Role;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.exceptions.role.RoleNotFoundException;
import ru.vibelab.tplatfom.exceptions.user.UserNotFoundException;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.mappers.TestResultMapper;
import ru.vibelab.tplatfom.mappers.UserMapper;
import ru.vibelab.tplatfom.repos.RoleRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.UserDeleteRequest;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;
import ru.vibelab.tplatfom.requests.auth.AuthRequest;
import ru.vibelab.tplatfom.requests.auth.RegistrationRequest;
import ru.vibelab.tplatfom.security.JwtProvider;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RoleRepository roleRepository;

    public AuthDTO authUser(AuthRequest request){
        User user = userRepo.findByUsername(request.getUsername());


//        if (user == null) {
//            return new AuthDTO("Login is not correct");
//        }
//
//        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            return new AuthDTO("Password is not correct");
//        }

        System.out.println(user.getRoles());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthDTO(user.getUsername(),
                token,
                "OK");
    }

    public RegistrationDTO registerUser(RegistrationRequest registrationRequest){
        boolean isExists = userRepo.findByUsername(registrationRequest.getUsername()) != null;

        if (isExists) {
            return new RegistrationDTO("Login is already taken");
        }

        Role role = roleRepository.findByName("Student");

        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.addRole(role);
        role.addUser(user);

        roleRepository.save(role);
        userRepo.save(user);

        return new RegistrationDTO("OK");
    }

    public Set<UserShortDTO> getAllUsers() {
        List<User> users = userRepo.findAll();

        return UserMapper.fromUsersToShortDTOs(users);
    }

    public UserDTO getUser(Long id) {
        return UserMapper.fromUserToDTO(userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        request.getRoles()
                        .forEach((role -> {
                            if (roleRepository.findByName(role.getName()) == null) {
                                throw new RoleNotFoundException("Role is not correct");
                            }
                        }));

        user.setRoles(request.getRoles());

        userRepo.save(user);

        return UserMapper.fromUserToDTO(user);
    }

    public void deleteUser(UserDeleteRequest request) {
        userRepo.delete(userRepo.findById(request.getId()).orElseThrow(
                () ->  new UserNotFoundException(request.getId())));
    }

    public List<TestDTO> getUserTests(Long id) {
        List<Test> tests = List.copyOf(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)).getTests());

        return TestMapper.fromTestsToDTOs(tests);
    }

    public List<TestResultDTO> getUserResults(Long id) {
        List<TestResult> results = List.copyOf(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        ).getTestResults());

        return TestResultMapper.fromTestsResultsToDTOs(results);
    }
}
