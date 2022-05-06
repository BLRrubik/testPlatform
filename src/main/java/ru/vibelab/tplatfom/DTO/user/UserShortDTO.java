package ru.vibelab.tplatfom.DTO.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.vibelab.tplatfom.DTO.role.RoleDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDTO {
    private Long id;
    private String username;
    private List<RoleDTO> roles;
}
