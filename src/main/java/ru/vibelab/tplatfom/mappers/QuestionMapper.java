package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.requests.QuestionRequest;

public class QuestionMapper {
    public static Question fromRequestToQuestion(QuestionRequest request) {
        Question question = new Question();
        question.setName(request.getName());
        question.setDescription(request.getDescription());
        question.setSolution(request.getSolution());
        return question;
    }

    public static QuestionDTO fromQuestionToDto(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setName(question.getName());
        dto.setDescription(question.getDescription());
        return dto;
    }
}
