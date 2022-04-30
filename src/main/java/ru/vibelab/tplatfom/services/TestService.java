package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.repos.QuestionRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.mappers.TestMapper;
import ru.vibelab.tplatfom.repos.TestRepository;

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
}
