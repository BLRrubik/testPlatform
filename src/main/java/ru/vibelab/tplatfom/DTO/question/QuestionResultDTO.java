package ru.vibelab.tplatfom.DTO.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResultDTO {
    private Long question_id;
    private String answer;
    private boolean right;
}
