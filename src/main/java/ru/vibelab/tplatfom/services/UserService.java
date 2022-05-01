package ru.vibelab.tplatfom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.TestDTO;
import ru.vibelab.tplatfom.DTO.TestResultDTO;
import ru.vibelab.tplatfom.DTO.UserDTO;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.mappers.TestResultMapper;
import ru.vibelab.tplatfom.mappers.UserMapper;
import ru.vibelab.tplatfom.repos.UserRepo;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Set<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper::fromEntityToDTO).collect(Collectors.toSet());
    }

    public UserDTO getUser(Long id) {
        return UserMapper.fromEntityToDTO(userRepo.findById(id).get());
    }

    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userRepo.findById(id).get();
        UserMapper.fromUpdateRequestToEntity(user, request);
        userRepo.save(user);
        return UserMapper.fromEntityToDTO(user);
    }

    public void deleteUser(Long id) {
        userRepo.delete(userRepo.findById(id).get());
    }

    public Set<TestDTO> getUserTests(Long id) {
        Set<Test> tests = userRepo.findById(id).get().getTests();
        return tests.stream().map(TestMapper::fromEntityToDto).collect(Collectors.toSet());
    }

    public Set<TestResultDTO> getUserResults(Long id) {
        Set<TestResult> results = userRepo.findById(id).get().getTestResults();
        return results.stream().map(TestResultMapper::fromEntityToDto).collect(Collectors.toSet());
    }
}
