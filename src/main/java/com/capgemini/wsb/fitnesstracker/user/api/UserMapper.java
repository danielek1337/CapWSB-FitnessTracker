package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Converts a User entity to its corresponding UserDto representation.
     *
     * @param user the User entity to be mapped
     * @return the mapped UserDto
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a UserDto to the corresponding User entity.
     *
     * @param userDto the UserDto to be converted
     * @return the mapped User entity
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }
    /**
     * Converts a UserDto to a User entity, retaining the state of the existing User entity.
     *
     * @param userDto the UserDto to be converted
     * @param user the existing User entity to retain state
     * @return the updated User entity
     */
    public User toUpdateEntity(UserDto userDto, User user) {
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if(userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        return user;
    }

}
