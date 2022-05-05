package ru.vibelab.tplatfom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.requests.BundledQuestionRequest;
import ru.vibelab.tplatfom.requests.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.QuestionRequest;
import ru.vibelab.tplatfom.services.QuestionService;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@RestController
@RequestMapping("/api/quest")
@RequiredArgsConstructor
public class QuestionController {
    @Autowired
    private final QuestionService questionService;

    @PostMapping()
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest request) throws URISyntaxException {
        Long id = questionService.create(request);
        return ResponseEntity.created(new URI(String.format("/api/quest/%d", id))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable(name = "id") Long id) {
        QuestionDTO question = questionService.getDtoById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<QuestionDTO> deleteQuestion(@PathVariable(name = "id") Long id) {
        QuestionDTO question = questionService.delete(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('Teacher')")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable(name = "id") Long id,
            @RequestBody BundledQuestionRequest request
    ) {
        Question question = questionService.updateQuestion(id, request);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> answerQuestion(
            @PathVariable(name = "id") Long id,
            @RequestBody QuestionAnswerRequest request,
            Principal principal
    ) {
        questionService.answerQuestion(id, request, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
