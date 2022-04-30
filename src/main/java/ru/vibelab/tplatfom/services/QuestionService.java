package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.exceptions.QuestionNotFoundException;
import ru.vibelab.tplatfom.exceptions.TestNotFoundException;
import ru.vibelab.tplatfom.mappers.QuestionMapper;
import ru.vibelab.tplatfom.repos.QuestionRepository;
import ru.vibelab.tplatfom.repos.QuestionResultRepository;
import ru.vibelab.tplatfom.repos.TestRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.QuestionRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Autowired
    private final TestRepository testRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionResultRepository questionResultRepository;

    @Autowired
    private final UserRepository userRepository;

    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException(id)
        );
    }

    public List<Question> getAllByTestId(Long id) {
        return questionRepository.findAllByTestId(id);
    }

    public Long create(Long testId, QuestionRequest request) {
        Test test = testRepository.findById(testId).orElseThrow(
                () -> new TestNotFoundException(testId)
        );
        Question question = QuestionMapper.fromRequest(request);
        question.setTest(test);
        return questionRepository.save(question).getId();
    }

    public Question delete(Long id) {
        Question question = getById(id);
        questionRepository.delete(question);
        return question;
    }

    public Long answerQuestion(Long questionId, QuestionAnswerRequest request) {
        Question question = getById(questionId);
        QuestionResult result = new QuestionResult();
        result.setQuestion(question);
        result.setAnswer(request.getAnswer());
        result.setRight(request.getAnswer().equals(question.getSolution()));

        // TODO: Добавить User
        User user = userRepository.findById(1L).orElse(null);
        result.setUser(user);

        return questionResultRepository.save(result).getId();
    }
}
