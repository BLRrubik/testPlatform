package ru.vibelab.tplatfom.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.services.TestService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    private ResponseEntity<List<Test>> getTests() {
        List<Test> tests = testService.getAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<String> createTest(@RequestBody TestRequest test) throws URISyntaxException {
        Long id = testService.createTest(test);
        return ResponseEntity.created(new URI(String.format("/api/test/%d", id))).body(null);
    }
}
