package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.Question;
import ru.vibelab.tplatfom.domain.QuestionResult;
import ru.vibelab.tplatfom.domain.User;

@Repository
public interface QuestionResultRepository extends JpaRepository<QuestionResult, Long> {
    void deleteAllByQuestion(Question question);

    QuestionResult findByQuestionAndUser(Question question, User user);
}
