package com.capgemini.wsb.fitnesstracker.training.api;
import java.util.Date;
import jakarta.annotation.Nullable;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;


public record TrainingDto(@Nullable Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
}
