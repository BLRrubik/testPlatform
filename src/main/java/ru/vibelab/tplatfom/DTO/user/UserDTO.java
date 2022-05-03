package ru.vibelab.tplatfom.DTO.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.vibelab.tplatfom.DTO.question.QuestionResultDTO;
import ru.vibelab.tplatfom.DTO.role.RoleDTO;
import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Role;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private List<RoleDTO> roles;
    private List<TestDTO> tests;
    private List<TestResultDTO> testResults;
    private List<QuestionResultDTO> questionResults;
}
