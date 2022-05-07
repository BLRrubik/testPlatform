package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.question.QuestionDTO;
import ru.vibelab.tplatfom.DTO.question.QuestionResultDTO;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.requests.question.BundledQuestionRequest;
import ru.vibelab.tplatfom.requests.question.QuestionRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static Question fromRequestToQuestion(BundledQuestionRequest request) {
        Question question = new Question();
        question.setName(request.getName());
        question.setDescription(request.getDescription());
        question.setSolution(request.getSolution());
        return question;
    }

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

    public static QuestionResultDTO fromQuestionResultToDTO(QuestionResult result) {
        QuestionResultDTO dto = new QuestionResultDTO();
        dto.setQuestion_id(result.getId());
        dto.setAnswer(result.getAnswer());
        dto.setRight(result.getRight());
        return dto;
    }

    public static List<QuestionResultDTO> fromQuestionResultsToDTOs(Set<QuestionResult> results) {
        return results.stream().map(QuestionMapper::fromQuestionResultToDTO).collect(Collectors.toList());
    }
}
