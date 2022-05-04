package ru.vibelab.tplatfom.DTO.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vibelab.tplatfom.DTO.question.QuestionResultDTO;
import ru.vibelab.tplatfom.DTO.role.RoleDTO;
import ru.vibelab.tplatfom.DTO.test.TestDTO;
import ru.vibelab.tplatfom.DTO.test.TestResultDTO;

import java.util.List;

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