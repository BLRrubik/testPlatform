package ru.vibelab.tplatfom.requests;

import lombok.*;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.Role;
import ru.vibelab.tplatfom.domain.TestResult;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private Set<Role> roles;
}
