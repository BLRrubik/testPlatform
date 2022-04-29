package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vibelab.tplatfom.domain.Role;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private Set<Role> roles;
}
