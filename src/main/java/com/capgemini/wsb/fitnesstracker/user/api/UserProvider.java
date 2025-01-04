package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;


public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves a list of users who are older than the specified date.
     *
     * @param date the date to compare users' birthdates against
     * @return a list of users who are older than the given date
     */
    List<User> getUsersOlderThan(LocalDate date);

    /**
     * Finds a user by their email address, ignoring case sensitivity.
     *
     * @param email the email address of the user to search for
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user is found
     */
    List<User> getUserByEmailIgnoreCase(String email);

}
