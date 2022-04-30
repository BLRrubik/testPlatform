package ru.vibelab.tplatfom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.services.TestService;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Transactional
public class TestController {
    @Autowired
    private final TestService testService;

    @GetMapping
    public ResponseEntity<List<Test>> getTests() {
        List<Test> tests = testService.getAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTest(@RequestBody TestRequest test) throws URISyntaxException {
        Long id = testService.createTest(test);
        return ResponseEntity.created(new URI(String.format("/api/test/%d", id))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTest(@PathVariable(name = "id") String id) {
        Optional<Test> test = testService.getById(Long.parseLong(id));
        if (test.isPresent()) {
            return new ResponseEntity<>(test.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable(name = "id") String id, @RequestBody TestRequest test) {
        try {
            testService.updateTest(Long.parseLong(id), test);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable(name = "id") String id) {
        try {
            Test test = testService.delete(Long.parseLong(id));
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
