package ru.vibelab.tplatfom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.test.TestShortDTO;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.requests.QuestionRequest;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.requests.UpdateTestRequest;
import ru.vibelab.tplatfom.services.QuestionService;
import ru.vibelab.tplatfom.services.TestService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final TestService testService;

    @Autowired
    private final QuestionService questionService;

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<TestShortDTO>> getTests() {
        List<TestShortDTO> tests = testService.getAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<String> createTest(@RequestBody @Valid TestRequest test) throws URISyntaxException {
        Long id = testService.create(test);
        return ResponseEntity.created(new URI(String.format("/api/test/%d", id))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getTest(@PathVariable(name = "id") String id) {
        Test test = testService.getById(Long.parseLong(id));
        return new ResponseEntity<>(TestMapper.fromTestToDTO(test), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<String> updateTest(
            @PathVariable(name = "id") String id,
            @RequestBody @Valid UpdateTestRequest request
    ) {
        testService.updateTest(Long.parseLong(id), request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<TestDTO> deleteTest(@PathVariable(name = "id") String id) {
        Test test = testService.delete(Long.parseLong(id));
        return new ResponseEntity<>(TestMapper.fromTestToDTO(test), HttpStatus.OK);
    }

    @GetMapping("/{id}/testresults")
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<List<TestResultDTO>> getResults(@PathVariable(name = "id") String id) {
        List<TestResultDTO> results = testService.getTestResults(Long.parseLong(id));
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}/quest")
    public ResponseEntity<List<QuestionDTO>> getTestQuestions(@PathVariable(name = "id") String id) {
        Test test = testService.getById(Long.parseLong(id));
        List<QuestionDTO> questions = questionService.getAllByTest(test);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
