package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vibelab.tplatfom.domain.UserRole;
import ru.vibelab.tplatfom.domain.keys.UserRoleKey;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
}
