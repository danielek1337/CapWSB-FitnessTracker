package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;


@Component
class UserSimpleMapper {

    /**
     * Converts a User entity to a UserSimpleDto representation.
     *
     * @param user the User entity to be converted
     * @return the corresponding UserSimpleDto representation of the User
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Converts a UserSimpleDto to a User entity.
     *
     * @param userDto the UserSimpleDto to be converted
     * @return the corresponding User entity
     */
    User toSimpleEntity(UserSimpleDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}