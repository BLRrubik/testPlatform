package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.test.TestShortDTO;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.requests.test.TestRequest;
import ru.vibelab.tplatfom.DTO.test.TestDTO;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    public static Test fromRequestToTest(TestRequest request) {
        Test test = new Test();
        test.setName(request.getName());
        Set<Question> questions = request.getQuestions().stream()
                .map(QuestionMapper::fromRequestToQuestion)
                .collect(Collectors.toSet());
        questions.forEach(question -> question.setTest(test));
        test.setQuestions(questions);
        return test;
    }
    
    public static TestDTO fromTestToDTO(Test test) {
        TestDTO dto = new TestDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setUser(UserMapper.fromUserToShortDTO(test.getUser()));
        dto.setQuestions(test.getQuestions().stream()
                .map(QuestionMapper::fromQuestionToDto)
                .collect(Collectors.toSet())
        );
        return dto;
    }

    public static List<TestDTO> fromTestsToDTOs(List<Test> tests) {
        return tests.stream()
                .map(TestMapper::fromTestToDTO)
                .collect(Collectors.toList());
    }

    /*
    public static Test fromDTOToTest(TestDTO testDTO) {
        Test test = new Test();
        test.setId(testDTO.getId());
        test.setName(testDTO.getName());
        test.setUser(testDTO.getUser());
        test.setQuestions(testDTO.getQuestions().forEach(QuestionMapper::));
        return test;
    }

    public static List<Test> fromDTOsToTests(List<TestDTO> testDTOs) {
        return testDTOs.stream()
                .map(TestMapper::fromDTOToTest)
                .collect(Collectors.toList());
    }
     */

    public static TestShortDTO fromTestToShortDto(Test test) {
        TestShortDTO dto = new TestShortDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        return dto;
    }

    public static List<TestShortDTO> fromTestsToShortDTOs(List<Test> tests) {
        return tests.stream()
                .map(TestMapper::fromTestToShortDto)
                .collect(Collectors.toList());
    }
}
