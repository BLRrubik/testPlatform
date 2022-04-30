package ru.vibelab.tplatfom.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vibelab.tplatfom.domain.Question;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {
    private String name;
    private String user;  // TODO: Заменить на UserRequest
    private Set<QuestionRequest> questions;
}
