package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.exceptions.question.QuestionNotFoundException;
import ru.vibelab.tplatfom.exceptions.test.TestNotFoundException;
import ru.vibelab.tplatfom.mappers.QuestionMapper;
import ru.vibelab.tplatfom.repos.QuestionRepository;
import ru.vibelab.tplatfom.repos.QuestionResultRepository;
import ru.vibelab.tplatfom.repos.TestRepository;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.QuestionAnswerRequest;
import ru.vibelab.tplatfom.requests.QuestionRequest;

import java.util.List;
import java.util.stream.Collectors;

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

    public Long create(Long testId, QuestionRequest request) {
        Test test = getTestById(testId);
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

    public void answerQuestion(Long id, QuestionAnswerRequest request) {
        Question question = getById(id);
        QuestionResult result = new QuestionResult();
        result.setQuestion(question);
        result.setAnswer(request.getAnswer());
        result.setRight(request.getAnswer().equals(question.getSolution()));

        // TODO: Устанавливать User
        User user = userRepository.findById(1L).orElse(null);
        result.setUser(user);

        questionResultRepository.save(result);
    }

    public Question updateQuestion(Long questionId, QuestionRequest request) {
        Question question = getById(questionId);
        question.setName(request.getName());
        question.setDescription(request.getDescription());
        question.setSolution(request.getSolution());
        return questionRepository.save(question);
    }
}
