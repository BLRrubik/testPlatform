package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    void deleteAllByTestId(Long id);
}
