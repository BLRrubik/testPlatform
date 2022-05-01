package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.domain.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    public static TestDTO fromTestToDTO(Test test) {
        return new TestDTO(test.getId(),
                test.getName(),
                test.getUser(),
                test.getQuestions(),
                test.getTestResults()
        );
    }

    public static List<TestDTO> fromTestsToDTOs(List<Test> tests) {
        return tests.stream()
                .map(TestMapper::fromTestToDTO)
                .collect(Collectors.toList());
    }

    public static Test fromDTOToTest(TestDTO testDTO) {
        return new Test(testDTO.getId(),
                testDTO.getName(),
                testDTO.getUser(),
                testDTO.getQuestions(),
                testDTO.getTestResults()
        );
    }

    public static List<Test> fromDTOsToTests(List<TestDTO> testDTOs) {
        return testDTOs.stream()
                .map(TestMapper::fromDTOToTest)
                .collect(Collectors.toList());
    }
}
