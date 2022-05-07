package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.test.TestShortDTO;
import ru.vibelab.tplatfom.domain.*;
import ru.vibelab.tplatfom.exceptions.test.TestNotFoundException;
import ru.vibelab.tplatfom.exceptions.test.TestNotStartedException;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.mappers.TestResultMapper;
import ru.vibelab.tplatfom.repos.*;
import ru.vibelab.tplatfom.requests.test.TestRequest;
import ru.vibelab.tplatfom.requests.test.UpdateTestRequest;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TestRepository testRepository;

    @Autowired
    private final TestResultRepository testResultRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionResultRepository questionResultRepository;

    public List<TestShortDTO> getAll(){
        return testRepository.findAll().stream()
                .map(TestMapper::fromTestToShortDto)
                .collect(Collectors.toList());
    }
    public Test getById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new TestNotFoundException(id));
    }

    public List<TestResultDTO> getTestResults(Long id) {
        Test test = getById(id);
        return testResultRepository.findAllByTest(test).stream()
                .map(TestResultMapper::fromTestResultToDTO)
                .collect(Collectors.toList());
    }

    public Long create(TestRequest request, Principal principal) {
        Test test = TestMapper.fromRequestToTest(request);
        User user = userRepository.findByUsername(principal.getName());
        test.setUser(user);
        testRepository.save(test);
        questionRepository.saveAll(test.getQuestions());
        return test.getId();
    }

    public void updateTest(Long id, UpdateTestRequest request) {
        Test test = getById(id);
        test.setName(request.getName());
        testRepository.save(test);
    }

    @Transactional
    public Test delete(Long id) {
        // TODO: Переписать с CASCADE, попробовать удалить аннотацию Transactional
        Test test = getById(id);
        List<Question> questions = questionRepository.findAllByTest(test);
        questions.forEach(questionResultRepository::deleteAllByQuestion);
        questionRepository.deleteAllByTest(test);
        testResultRepository.deleteAllByTest(test);
        testRepository.delete(test);
        return test;
    }

    public TestResult finish(Long id, Principal principal) {
        Test test = getById(id);
        User user = userRepository.findByUsername(principal.getName());
        TestResult testResult = testResultRepository.findByUserAndTest(user, test);

        if (testResult == null) {
            throw new TestNotStartedException(id);
        }

        long score = 0L;

        for (Question question : test.getQuestions()) {
            QuestionResult questionResult = questionResultRepository.findByQuestionAndUser(question, user);
            if (questionResult != null && questionResult.getRight()) {
                score++;
            }
        }

        testResult.setFinished(true);
        testResult.setScore(score);
        testResultRepository.save(testResult);
        return testResult;
    }
}
