package ru.vibelab.tplatfom.requests.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateByAdminRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Set<RoleUpdateRequest> roles;
}
