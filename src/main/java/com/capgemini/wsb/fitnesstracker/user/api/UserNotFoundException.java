package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    /**
     * Creates a new UserNotFoundException with the provided detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    private UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new UserNotFoundException with the specified detail message and cause.
     *
     * @param id the ID of the user that was not found
     */
    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
