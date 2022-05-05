package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.domain.*;
import ru.vibelab.tplatfom.exceptions.question.QuestionNotFoundException;
import ru.vibelab.tplatfom.exceptions.test.TestNotFoundException;
import ru.vibelab.tplatfom.mappers.QuestionMapper;
import ru.vibelab.tplatfom.repos.*;
import ru.vibelab.tplatfom.requests.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.BundledQuestionRequest;
import ru.vibelab.tplatfom.requests.QuestionRequest;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Autowired
    private final TestRepository testRepository;

    @Autowired final TestResultRepository testResultRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionResultRepository questionResultRepository;

    @Autowired
    private final UserRepository userRepository;

    private Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    public QuestionDTO getDtoById(Long id) {
        Question question = getById(id);
        return QuestionMapper.fromQuestionToDto(question);
    }

    private Test getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new TestNotFoundException(id));
    }

    public List<QuestionDTO> getAllByTest(Test test) {
        return questionRepository.findAllByTest(test).stream()
                .map(QuestionMapper::fromQuestionToDto)
                .collect(Collectors.toList());
    }

    public Long create(QuestionRequest request) {
        Test test = getTestById(request.getTestId());
        Question question = QuestionMapper.fromRequestToQuestion(request);
        question.setTest(test);
        return questionRepository.save(question).getId();
    }

    public QuestionDTO delete(Long id) {
        Question question = getById(id);
        questionResultRepository.deleteAllByQuestion(question);
        questionRepository.delete(question);
        return QuestionMapper.fromQuestionToDto(question);
    }

    public Question updateQuestion(Long questionId, BundledQuestionRequest request) {
        Question question = getById(questionId);
        question.setName(request.getName());
        question.setDescription(request.getDescription());
        question.setSolution(request.getSolution());
        return questionRepository.save(question);
    }

    public void answerQuestion(Long id, QuestionAnswerRequest request, Principal principal) {
        Question question = getById(id);
        Test test = question.getTest();
        User user = userRepository.findByUsername(principal.getName());

        if (!testResultRepository.hasUserActiveTest(test, user)) {
            TestResult result = new TestResult();
            result.setScore(0L);
            result.setFinished(false);
            result.setUser(user);
            result.setTest(test);
            testResultRepository.save(result);
        }

        QuestionResult result;

        if (questionResultRepository.existsByQuestionAndUser(question, user)) {
            result = questionResultRepository.getById(id);
        } else {
            result = new QuestionResult();
            result.setQuestion(question);
            result.setUser(user);
        }

        result.setAnswer(request.getAnswer());
        result.setRight(request.getAnswer().equals(question.getSolution()));
        questionResultRepository.save(result);
    }
}
