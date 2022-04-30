package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.exceptions.TestNotFoundException;
import ru.vibelab.tplatfom.repos.QuestionRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.repos.TestRepository;
import ru.vibelab.tplatfom.requests.UpdateTestRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<Test> getAll(){
        return testRepository.findAll();
    }

    public Test getById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new TestNotFoundException(id));
    }

    public Long create(TestRequest request) {
        Test requestTest = TestMapper.fromRequest(request);

        requestTest.setUser(userRepository.findById(1L).orElse(null));
        // TODO: Заменить на пользователя

        Test test = testRepository.save(requestTest);
        requestTest.getQuestions().forEach(item -> item.setTest(test));
        questionRepository.saveAll(test.getQuestions());
        return test.getId();
    }

    public void updateTest(Long id, UpdateTestRequest request) {
        Test test = testRepository.getById(id);

        // TODO: Проверка на пользователя

        test.setName(request.getName());
        testRepository.save(test);
    }

    @Transactional
    public Test delete(Long id) {
        Test test = testRepository.getById(id);
        questionRepository.deleteAllByTestId(id);
        testRepository.delete(test);
        return test;
    }
}
