package ru.vibelab.tplatfom.requests.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    @NotNull
    private Long test;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String solution;
}
