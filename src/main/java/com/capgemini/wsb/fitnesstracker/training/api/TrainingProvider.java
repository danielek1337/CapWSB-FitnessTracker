package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.List;
import java.util.Date;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Fetches all training sessions for a given user object.
     *
     * @param user the user object whose training sessions are to be retrieved
     * @return A list of training sessions linked to the specified user.
     */
    List<Training> findByUserObject(User user);

    /**
     * Fetches all training sessions of a particular activity type.
     *
     * @param activityType the type of activity to filter training sessions
     * @return A list of training sessions matching the specified activity type.
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Fetches all available training sessions.
     *
     * @return A list of all training sessions.
     */
    List<Training> findAllTrainings();

    /**
     * Fetches all training sessions that concluded after a specified time.
     *
     * @param endTime the time after which trainings must have been completed
     * @return A list of training sessions finished after the specified time.
     */
    List<Training> findFinishedTrainings(Date endTime);

    /**
     * Fetches all training sessions for a specific user by their ID.
     *
     * @param userId the unique identifier of the user
     * @return A list of training sessions associated with the specified user ID.
     */
    List<Training> findTrainingByUser(Long userId);


}
