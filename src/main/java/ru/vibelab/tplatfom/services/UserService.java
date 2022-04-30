package ru.vibelab.tplatfom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.repos.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
