package ru.vibelab.tplatfom.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestShortDTO {
    private Long id;
    private String name;
    private UserShortDTO user;
}
