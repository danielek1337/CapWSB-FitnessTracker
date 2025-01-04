package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * A lightweight Data Transfer Object (DTO) representing a simplified version of the User entity.
 * This DTO is used for scenarios where only basic user details are required.
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName){

}