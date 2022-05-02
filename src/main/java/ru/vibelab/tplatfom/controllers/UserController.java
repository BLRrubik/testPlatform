package ru.vibelab.tplatfom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.user.UserDTO;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;
import ru.vibelab.tplatfom.requests.UserDeleteRequest;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;
import ru.vibelab.tplatfom.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Set<UserShortDTO>> getAllUsers() {
        return ResponseEntity.of(Optional.of(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserShortDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(userService.getUser(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.of(Optional.of(userService.updateUser(id, request)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@RequestBody UserDeleteRequest request) {
        userService.deleteUser(request);
    }

//    @GetMapping("/test")
//    public ResponseEntity<List<TestDTO>> getUserTests() {
//        return null;
//    }

    @GetMapping("/users/{id}/testresult")
    public ResponseEntity<List<TestResultDTO>> getUserResults(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(userService.getUserResults(id)));
    }
}
