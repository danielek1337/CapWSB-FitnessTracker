package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;
    private final UserProvider userProvider;

    /**
     * Converts a Training DTO with a User ID to a Training entity.
     *
     * @param trainingDto the Training DTO to convert
     * @return the corresponding Training entity
     * @throws IllegalArgumentException if the user with the provided ID is not found
     */
    public Training toEntity(TrainingDtoWithUserId trainingDto) {
        User user = userProvider.getUser(trainingDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + trainingDto.userId() + " not found"));
        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }

    /**
     * Converts a Training entity to its corresponding Training DTO.
     *
     * @param training the Training entity to convert
     * @return the Training DTO representation of the entity
     */
    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
