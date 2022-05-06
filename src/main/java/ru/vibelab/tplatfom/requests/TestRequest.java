package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {
    @NotNull
    private String name;
    @NotNull
    private String user;
    private Set<QuestionRequest> questions;
}
