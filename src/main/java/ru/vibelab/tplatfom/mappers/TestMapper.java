package ru.vibelab.tplatfom.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.repos.UserRepository;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.services.UserService;

import java.util.Set;
import java.util.stream.Collectors;

public class TestMapper {
    public static Test fromDto(TestRequest dto) {
        Test test = new Test();
        test.setName(dto.getName());

        // TODO: test.setUser(dto.getUser());

        Set<Question> questions = dto.getQuestions()
                .stream()
                .map(QuestionMapper::fromDto)
                .collect(Collectors.toSet());
        test.setQuestions(questions);

        return test;
    }
}
