package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.domain.User;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllByTest(Test test);

    List<TestResult> findAllByUser(User user);

    void deleteAllByTest(Test test);

    @Query("SELECT COUNT(result) > 0 FROM TestResult result " +
            "WHERE result.test = :test AND result.user = :user and result.finished = False"
    )
    boolean hasUserActiveTest(Test test, User user);

    TestResult findByUserAndTest(User user, Test test);
}
