package ru.vibelab.tplatfom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.user.UserDTO;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.exceptions.users.UserNotFoundException;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.mappers.TestResultMapper;
import ru.vibelab.tplatfom.mappers.UserMapper;
import ru.vibelab.tplatfom.repos.UserRepo;
import ru.vibelab.tplatfom.requests.UserDeleteRequest;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Set<UserShortDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return UserMapper.fromUsersToShortDTOs(users);
    }

    public UserShortDTO getUser(Long id) {
        return UserMapper.fromUserToShortDTO(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("No user with id: " + id))
        );
    }

    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("No user with id: " + id)
        );
        UserMapper.fromUpdateRequestToUser(user, request);
        userRepo.save(user);
        return UserMapper.fromUserToDTO(user);
    }

    public void deleteUser(UserDeleteRequest request) {
        Long id = request.getId();
        userRepo.delete(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("No user with id: " + id)
        ));
    }

    public List<TestDTO> getUserTests(Long id) {
        List<Test> tests = List.copyOf(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("No user with id: " + id)
        ).getTests());
        return TestMapper.fromTestsToDTOs(tests);
    }

    public List<TestResultDTO> getUserResults(Long id) {
        List<TestResult> results = List.copyOf(userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("No user with id: " + id)
        ).getTestResults());
        return TestResultMapper.fromTestsResultsToDTOs(results);
    }
}
