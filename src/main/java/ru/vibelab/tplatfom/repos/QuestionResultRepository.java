package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.QuestionResult;

@Repository
public interface QuestionResultRepository extends JpaRepository<QuestionResult, Long> {
}
