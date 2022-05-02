package ru.vibelab.tplatfom.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private Long id;
    private String name;
    private UserShortDTO user;
    private Set<QuestionDTO> questions;
}
