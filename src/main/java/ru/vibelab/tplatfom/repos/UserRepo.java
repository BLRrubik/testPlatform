package ru.vibelab.tplatfom.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vibelab.tplatfom.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {


}
