package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vibelab.tplatfom.domain.Test;
import ru.vibelab.tplatfom.domain.keys.TestKey;

public interface TestRepository extends JpaRepository<Test, TestKey> {
}
