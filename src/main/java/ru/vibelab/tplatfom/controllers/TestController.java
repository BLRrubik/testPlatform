package ru.vibelab.tplatfom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.requests.*;
import ru.vibelab.tplatfom.services.QuestionService;
import ru.vibelab.tplatfom.services.TestService;

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
    public ResponseEntity<List<Test>> getTests() {
        List<Test> tests = testService.getAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTest(@PathVariable(name = "id") String id) {
        Test test = testService.getById(Long.parseLong(id));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTest(@RequestBody TestRequest test) throws URISyntaxException {
        Long id = testService.create(test);
        return ResponseEntity.created(new URI(String.format("/api/test/%d", id))).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateTest(
            @PathVariable(name = "id") String id,
            @RequestBody UpdateTestRequest request
    ) {
        testService.updateTest(Long.parseLong(id), request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable(name = "id") String id) {
        Test test = testService.delete(Long.parseLong(id));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/{id}/testresults")
    public ResponseEntity<List<TestResult>> getResults(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(testService.getTestResults(Long.parseLong(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/quest")
    public ResponseEntity<List<Question>> getTestQuestions(@PathVariable(name = "id") String id) {
        Test test = testService.getById(Long.parseLong(id));
        List<Question> questions = questionService.getAllByTestId(test.getId());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/{id}/quest")
    public ResponseEntity<String> createQuestion(
            @PathVariable(name = "id") String id,
            @RequestBody QuestionRequest request
    ) throws URISyntaxException {
        Long test_id = Long.parseLong(id);
        Long question_id = questionService.create(test_id, request);
        return ResponseEntity.created(
                new URI(String.format("/api/test/%d/quest/%d", test_id, question_id))
        ).build();
    }

    @GetMapping("/{test_id}/quest/{quest_id}")
    public ResponseEntity<Question> getQuestion(
            @PathVariable(name = "test_id") String testId,
            @PathVariable(name = "quest_id") String questId
    ) {
        // TODO: testId лишний?
        Question question = questionService.getById(Long.parseLong(questId));
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{test_id}/quest/{quest_id}")
    public ResponseEntity<Question> deleteQuestion(
            @PathVariable(name = "test_id") String testId,
            @PathVariable(name = "quest_id") String questId
    ) {
        // TODO: testId лишний?
        Question question = questionService.delete(Long.parseLong(questId));
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/{test_id}/quest/{quest_id}")
    public ResponseEntity<String> answerQuestion(
            @PathVariable(name = "test_id") String testId,
            @PathVariable(name = "quest_id") String questId,
            @RequestBody QuestionAnswerRequest request
    ) throws URISyntaxException {
        // TODO: testId лишний?
        // TODO: Проверять правильность ответа, возращать результат
        // TODO: Добавить User
        Long answerId = questionService.answerQuestion(Long.parseLong(questId), request);
        return ResponseEntity.created(
                new URI(String.format("/api/test/%s/quest/%d", testId, answerId))
        ).build();
    }

    @PostMapping("/{test_id}/quest/{quest_id}/edit")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable(name = "test_id") String testId,
            @PathVariable(name = "quest_id") String questId,
            @RequestBody QuestionRequest request
    ) {
        // TODO: testId лишний?
        Question question = questionService.updateQuestion(Long.parseLong(questId), request);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<TestResult>> userResults(
            @PathVariable(name = "id") String id,
            @RequestBody UserRequest request
    ) {
        // TODO: testId лишний?
        List<TestResult> results = testService.getUserResults(Long.parseLong(id), request.getId());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
