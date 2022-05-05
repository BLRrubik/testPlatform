package ru.vibelab.tplatfom.repos;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.User;

import java.util.List;
import java.util.function.Function;

@Repository
public interface QuestionResultRepository extends JpaRepository<QuestionResult, Long> {
    boolean existsByQuestionAndUser(Question question, User user);

    void deleteAllByQuestion(Question question);

    QuestionResult findByQuestionAndUser(Question question, User user);
}
