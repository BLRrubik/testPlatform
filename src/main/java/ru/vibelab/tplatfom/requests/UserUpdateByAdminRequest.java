package ru.vibelab.tplatfom.requests;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateByAdminRequest {
    private String username;
    private String password;
    private Set<RoleUpdateRequest> roles;
}
