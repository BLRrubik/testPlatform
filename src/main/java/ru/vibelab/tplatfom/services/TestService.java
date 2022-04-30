package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.mappers.QuestionMapper;
import ru.vibelab.tplatfom.repos.QuestionRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.repos.TestRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final UserRepository userRepository;

    public Long createTest(TestRequest request) {
        Test test = TestMapper.fromDto(request);
        test.setUser(userRepository.findById(1L).orElse(null));  // TODO: Заменить на пользователя
        Test savedTest = testRepository.save(test);
        test.getQuestions().forEach(item -> item.setTest(savedTest));
        questionRepository.saveAll(savedTest.getQuestions());
        return savedTest.getId();
    }

    public List<Test> getAll(){
        return testRepository.findAll();
    }

    public Optional<Test> getById(Long id) {
        return testRepository.findById(id);
    }

    public void updateTest(Long id, TestRequest request) {
        Test test = testRepository.findById(id).orElseThrow();
        test.setName(request.getName());
        test.setQuestions(
                request.getQuestions().stream().map(QuestionMapper::fromDto).collect(Collectors.toSet())
        );
        questionRepository.deleteAllByTestId(id);
        test.getQuestions().forEach(item -> item.setTest(test));
        questionRepository.saveAll(test.getQuestions());
        testRepository.save(test);
    }

    public Test delete(Long id) {
        Test test = testRepository.findById(id).orElseThrow();
        questionRepository.deleteAllByTestId(id);
        testRepository.delete(test);
        return test;
    }
}
