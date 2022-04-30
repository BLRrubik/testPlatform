package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.repos.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;
}
