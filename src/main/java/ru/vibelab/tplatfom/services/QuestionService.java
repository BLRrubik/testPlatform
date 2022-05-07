package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.domain.*;
import ru.vibelab.tplatfom.exceptions.question.QuestionNotFoundException;
import ru.vibelab.tplatfom.exceptions.test.TestFinishedException;
import ru.vibelab.tplatfom.exceptions.test.TestNotFoundException;
import ru.vibelab.tplatfom.mappers.QuestionMapper;
import ru.vibelab.tplatfom.repos.*;
import ru.vibelab.tplatfom.requests.question.BundledQuestionRequest;
import ru.vibelab.tplatfom.requests.question.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.question.QuestionRequest;

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
        Test test = getTestById(request.getTest());
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

        TestResult testResult = null;

        for (TestResult tempResult : user.getTestResults()) {
            if (tempResult.getTest() == test) {
                if (tempResult.getFinished()) {
                    throw new TestFinishedException(test.getId());
                } else {
                    testResult = tempResult;
                    break;
                }
            }
        }

        if (testResult == null) {
            testResult = new TestResult();
            testResult.setScore(0L);
            testResult.setFinished(false);
            testResult.setUser(user);
            testResult.setTest(test);
            testResultRepository.save(testResult);
        }

        QuestionResult questionResult = null;

        for (QuestionResult tempResult : user.getQuestionResults()) {
            if (tempResult.getQuestion() == question) {
                questionResult = tempResult;
                break;
            }
        }

        if (questionResult == null) {
            questionResult = new QuestionResult();
            questionResult.setQuestion(question);
            questionResult.setUser(user);
        }

        questionResult.setAnswer(request.getAnswer());
        questionResult.setRight(request.getAnswer().equals(question.getSolution()));
        questionResultRepository.save(questionResult);
    }
}
