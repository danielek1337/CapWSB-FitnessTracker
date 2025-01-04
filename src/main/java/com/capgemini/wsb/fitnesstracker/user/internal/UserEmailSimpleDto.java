package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * A simplified Data Transfer Object (DTO) representing the User entity.
 * This class provides a basic version of user data for scenarios where full user details are not required.
 */

public record UserEmailSimpleDto(@Nullable Long id, String email){

}