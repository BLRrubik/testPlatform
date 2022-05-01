package ru.vibelab.tplatfom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;
import ru.vibelab.tplatfom.services.UserService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        return ResponseEntity.of(Optional.of(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(userService.getUser(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.of(Optional.of(userService.updateUser(id, request)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    @GetMapping("/test")
//    public ResponseEntity<Set<TestDTO>> getUserTests() {
//        return null;
//    }

    @GetMapping("/users/{id}/testresult")
    public ResponseEntity<Set<TestResultDTO>> getUserResults(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(userService.getUserResults(id)));
    }
}
