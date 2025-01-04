package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations and custom queries on {@link Training} entities.
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves a list of trainings that are linked to a specific user object.
     *
     * @param user the user associated with the trainings
     * @return a list of trainings for the specified user
     */
    default List<Training> findByUserObject(User user) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser(), user))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all trainings associated with a specific user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of trainings linked to the specified user ID
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all trainings that have finished after a certain date.
     *
     * @param afterTime the date to compare the training's end time with
     * @return a list of trainings that end after the provided date
     */

    default List<Training> findByEndTimeAfter(Date afterTime) {
        return findAll().stream()
                .filter(training -> Objects.compare(training.getEndTime(), afterTime, Comparator.naturalOrder()) > 0)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of trainings based on a specific activity type.
     *
     * @param activityType the activity type to filter the trainings by
     * @return a list of trainings for the specified activity type
     */

    default List<Training> findByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), activityType))
                .collect(Collectors.toList());
    }
}
