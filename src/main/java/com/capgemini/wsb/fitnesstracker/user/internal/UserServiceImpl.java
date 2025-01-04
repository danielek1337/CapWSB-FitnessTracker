package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;


    /**
     * Creates a new user with the provided details.
     *
     * @param user the User entity to be created
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to be retrieved
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user is found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to be retrieved
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user is found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get all the Users
     * @return List of Users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves all users who are older than the specified date.
     *
     * @param date the date to compare against the users' birthdates
     * @return a list of users who are older than the given date
     */
    @Override
    public List<User> getUsersOlderThan(LocalDate date) {
        return userRepository.findByBirthDateBefore(date);
    }

    /**
     * Retrieves users by their email address, ignoring case.
     *
     * @param email the email address of the user to be searched for
     * @return a list of users with the matching email (case-insensitive)
     */
    @Override
    public List<User> getUserByEmailIgnoreCase(final String email) {
        return userRepository.findByEmailFragmentIgnoreCase(email);
    }

    /**
     * Updates the details of an existing user.
     *
     * @param user the User entity containing updated information
     * @return the updated User entity
     */
    @Override
    public User updateUser(final User user) {
        log.info("Updating User {}", user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("User has no DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes an existing user based on their ID.
     *
     * @param userId the ID of the user to be deleted
     */
    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

}