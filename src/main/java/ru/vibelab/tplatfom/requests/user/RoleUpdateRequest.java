package ru.vibelab.tplatfom.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private String name;
}
