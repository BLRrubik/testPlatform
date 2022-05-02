package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Role;
import ru.vibelab.tplatfom.domain.TestResult;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDeleteRequest {
    private Long id;
}
