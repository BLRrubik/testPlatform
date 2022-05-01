package ru.vibelab.tplatfom.DTO.test;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDTO {
    private Long id;
    private Long score;
    private User user;
    private Test test;
}
