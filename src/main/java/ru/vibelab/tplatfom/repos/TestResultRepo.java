package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.TestResult;
import ru.vibelab.tplatfom.domain.User;

import java.util.List;

@Repository
public interface TestResultRepo extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllByUser(User user);
}

