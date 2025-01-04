package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing a User entity.
 * This class is used to transfer user data between layers or across services.
 */

public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}
