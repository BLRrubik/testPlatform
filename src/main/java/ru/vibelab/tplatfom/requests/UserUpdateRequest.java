package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Role;
import ru.vibelab.tplatfom.domain.TestResult;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private Set<Role> roles;
    private Set<TestResult> testResults;
    private Set<QuestionResult> questionResults;
}
