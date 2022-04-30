package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.requests.QuestionRequest;

public class QuestionMapper {
    public static Question fromRequest(QuestionRequest request) {
        Question question = new Question();
        question.setName(request.getName());
        question.setDescription(request.getDescription());
        question.setSolution(request.getSolution());
        return question;
    }
}
