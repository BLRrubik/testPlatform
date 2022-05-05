package ru.vibelab.tplatfom.requests;

import lombok.*;
import ru.vibelab.tplatfom.domain.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequest {
    @NotNull
    private Long id;
}
