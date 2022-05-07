package ru.vibelab.tplatfom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.requests.question.BundledQuestionRequest;
import ru.vibelab.tplatfom.requests.question.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.question.QuestionRequest;
import ru.vibelab.tplatfom.services.QuestionService;

import javax.validation.Valid;
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
    public ResponseEntity<String> createQuestion(
            @RequestBody @Valid QuestionRequest request
    ) throws URISyntaxException {
        Long id = questionService.create(request);
        return ResponseEntity.created(new URI(String.format("/api/quest/%d", id))).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
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
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid BundledQuestionRequest request
    ) {
        QuestionDTO question = questionService.updateQuestion(id, request);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> answerQuestion(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid QuestionAnswerRequest request,
            Principal principal
    ) {
        questionService.answerQuestion(id, request, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
