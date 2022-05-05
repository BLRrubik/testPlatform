package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultShortDTO;
import ru.vibelab.tplatfom.domain.TestResult;

import java.util.List;
import java.util.stream.Collectors;

public class TestResultMapper {
    public static TestResultDTO fromTestResultToDTO(TestResult testResult) {
        return new TestResultDTO(testResult.getId(),
                testResult.getScore(),
                TestMapper.fromTestToShortDto(testResult.getTest()),
                UserMapper.fromUserToShortDTO(testResult.getUser())
        );
    }

    public static List<TestResultDTO> fromTestsResultsToDTOs(List<TestResult> testResults) {
        return testResults.stream()
                .map(TestResultMapper::fromTestResultToDTO)
                .collect(Collectors.toList());
    }

    public static TestResultShortDTO fromTestResultToShortDTO(TestResult testResult) {
        return new TestResultShortDTO(testResult.getId(),
                testResult.getScore(),
                TestMapper.fromTestToShortDto(testResult.getTest())
        );
    }

    public static List<TestResultShortDTO> fromTestsResultsToShortDTOs(List<TestResult> testResults) {
        return testResults.stream()
                .map(TestResultMapper::fromTestResultToShortDTO)
                .collect(Collectors.toList());
    }



    /*
    public static TestResult fromDTOToTestResult(TestResultDTO testResultDTO) {
        return new TestResult(testResultDTO.getId(),
                testResultDTO.getScore(),
                testResultDTO.getUser(),
                testResultDTO.getTest()
        );
    }


    public static List<TestResult> fromDTOsToTestResults(List<TestResultDTO> testResultDTOs) {
        return testResultDTOs.stream()
                .map(TestResultMapper::fromDTOToTestResult)
                .collect(Collectors.toList());
    }
    */
}
