package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.requests.QuestionRequest;

public class QuestionMapper {
    public static Question fromDto(QuestionRequest dto) {
        Question question = new Question();
        question.setName(dto.getName());
        question.setDescription(dto.getDescription());
        question.setSolution(dto.getSolution());
        return question;
    }
}
