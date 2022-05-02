package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vibelab.tplatfom.domain.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {


}
