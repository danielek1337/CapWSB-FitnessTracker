package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserEmailSimpleMapper {

    /**
     * Converts a User entity to a UserEmailSimpleDto.
     *
     * @param user the User entity to be mapped
     * @return a UserEmailSimpleDto representing the user
     */
    UserEmailSimpleDto toEmailSimpleDto(User user) {
        return new UserEmailSimpleDto(user.getId(), user.getEmail());
    }

    /**
     * Converts a UserEmailSimpleDto to a User entity.
     *
     * @param userDto the UserEmailSimpleDto to be mapped
     * @return a User entity corresponding to the provided DTO
     */
    User toSimpleEmailEntity(UserEmailSimpleDto userDto) {
        return new User(null, null, null, userDto.email());
    }
}