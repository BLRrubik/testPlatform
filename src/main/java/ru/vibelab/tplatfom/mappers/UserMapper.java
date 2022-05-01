package ru.vibelab.tplatfom.mappers;

import ru.vibelab.tplatfom.DTO.user.UserDTO;
import ru.vibelab.tplatfom.DTO.user.UserShortDTO;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.requests.UserUpdateRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO fromUserToDTO(User user) {
        return new UserDTO(user.getUsername(),
                user.getRoles(),
                user.getTests(),
                user.getTestResults(),
                user.getQuestionResults()
        );
    }

    public static Set<UserDTO> fromUsersToDTOs(List<User> users) {
        return users.stream()
                .map(UserMapper::fromUserToDTO)
                .collect(Collectors.toSet());
    }

    public static UserShortDTO fromUserToShortDTO(User user) {
        return new UserShortDTO(user.getId(),
                user.getUsername()
        );
    }

    public static Set<UserShortDTO> fromUsersToShortDTOs(List<User> users){
        return users.stream()
                .map(UserMapper::fromUserToShortDTO)
                .collect(Collectors.toSet());
    }

    public static void fromUpdateRequestToUser(User user, UserUpdateRequest request) {
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRoles(request.getRoles());
        user.setTestResults(request.getTestResults());
        user.setQuestionResults(request.getQuestionResults());
    }
}
