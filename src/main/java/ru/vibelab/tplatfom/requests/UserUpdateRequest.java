package ru.vibelab.tplatfom.requests;

import lombok.*;

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
}
