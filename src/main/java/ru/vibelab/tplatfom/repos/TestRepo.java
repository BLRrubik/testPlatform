package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.User;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    List<Test> findAllByUser(User user);
}

