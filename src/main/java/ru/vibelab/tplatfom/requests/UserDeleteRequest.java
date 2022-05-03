package ru.vibelab.tplatfom.requests;

import lombok.*;
import ru.vibelab.tplatfom.domain.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequest {
    private Long id;
}
