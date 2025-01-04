package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    /**
     * Retrieves a list of all users.
     *
     * @return a list of UserDto objects representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Adds a new user to the system.
     *
     * @param userDto the UserDto containing the details of the user to be added
     * @return the created User entity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        try {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add user with email: " + userDto.email());
        }
        return null;
    }

    /**
     * Retrieves a list of all users in a simplified format.
     *
     * @return a list of UserSimpleDto objects representing all users in a basic form
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::toSimpleDto)
                .toList();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId the ID of the user to be fetched
     * @return a UserDto representing the user with the specified ID
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find a user with ID: " + userId));
    }

    /**
     * Retrieves a list of users based on their email address.
     *
     * @param email the email address to search for
     * @return a list of UserEmailSimpleDto objects representing users with the specified email
     */
    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userEmailSimpleMapper::toEmailSimpleDto)
                .toList();
    }

    /**
     * Retrieves a list of users who are older than the specified date.
     *
     * @param time the date to compare users' birthdates against
     * @return a list of UserDto objects representing users older than the given date
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing user with the provided details.
     *
     * @param userId the ID of the user to be updated
     * @param userDto the UserDto containing the updated information
     * @return the updated User entity
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
            try {
                User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
                User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);
                return userService.updateUser(updatedUser);
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot update user with ID: " + userId + " with error: " + e.getMessage());
            }
        }

    /**
     * Deletes an existing user by their unique ID.
     *
     * @param userId the ID of the user to be deleted
     */
    @DeleteMapping("/{userId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteUser(@PathVariable Long userId) {
            try {
                userService.deleteUser(userId);
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot delete user with ID: " + userId + " with error: " + e.getMessage());
            }
        }
}