package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Implementation of services for managing training entities.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    /**
     * Repository interface for accessing training data.
     */
    private final TrainingRepository trainingRepository;

    /**
     * Retrieves all training sessions for a specific user based on their user ID.
     *
     * @param userId the ID of the user whose trainings are to be fetched
     * @return a list of trainings associated with the specified user ID
     */
    @Override
    public List<Training> findTrainingByUser(final Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Partially updates the properties of an existing training session.
     *
     * @param id      the ID of the training session to update
     * @param updates a map containing the fields to update and their new values
     * @return the updated training session entity
     */
    @Override
    public Training partiallyUpdateTraining(Long id, Map<String, Object> updates) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));
        updates.forEach((key, value) -> {
            switch (key) {
                case "startTime":
                    training.setStartTime((Date) value);
                    break;
                case "activityType":
                    training.setActivityType(ActivityType.valueOf((String) value));
                    break;
                case "userId":
                    training.setUser((User) value);
                    break;
                case "averageSpeed":
                    training.setAverageSpeed((Double) value);
                    break;
                case "distance":
                    training.setDistance((Double) value);
                    break;
                case "endTime":
                    training.setEndTime((Date) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field unknown: " + key);
            }
        });
        return trainingRepository.save(training);
    }

    /**
     * Deletes a training session by its ID.
     *
     * @param id the ID of the training session to be deleted
     */
    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    /**
     * Retrieves all training sessions stored in the system.
     *
     * @return a list of all available training sessions
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Fetches all training sessions completed after a specified time.
     *
     * @param afterTime the time after which trainings must have ended
     * @return a list of trainings completed after the given time
     */
    @Override
    public List<Training> findFinishedTrainings(final Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    /**
     * Retrieves training sessions linked to a specific user object.
     *
     * @param user the user object whose trainings are to be fetched
     * @return a list of trainings linked to the provided user object
     */
    @Override
    public List<Training> findByUserObject(User user) {
        return trainingRepository.findByUserObject(user);
    }

    /**
     * Retrieves all training sessions of a particular activity type.
     *
     * @param activityType the type of activity to filter trainings by
     * @return a list of trainings matching the specified activity type
     */
    @Override
    public List<Training> findTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Creates and saves a new training session.
     *
     * @param training the training session to be created
     * @return the newly saved training session entity
     */
    @Override
    public Training createTraining(Training training) {
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training ID should not be set before creation");
        }
        return trainingRepository.save(training);
    }

    /**
     * Updates an existing training session based on its ID.
     *
     * @param id              the ID of the training session to update
     * @param updatedTraining the updated training session entity
     * @return the saved training session after applying updates
     */
    @Override
    public Training updateTraining(Long id, Training updatedTraining) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Didn't find a training for ID: " + id));

        if (updatedTraining.getStartTime() != null) {
            existingTraining.setStartTime(updatedTraining.getStartTime());
        }
        if (updatedTraining.getEndTime() != null) {
            existingTraining.setEndTime(updatedTraining.getEndTime());
        }
        if (updatedTraining.getActivityType() != null) {
            existingTraining.setActivityType(updatedTraining.getActivityType());
        }
        if (updatedTraining.getDistance() != 0) {
            existingTraining.setDistance(updatedTraining.getDistance());
        }
        if (updatedTraining.getAverageSpeed() != 0) {
            existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
        }
        return trainingRepository.save(existingTraining);
    }

    /**
     * Fetches a specific training session by its ID.
     *
     * @param trainingId the ID of the training session to retrieve
     * @return an optional containing the training session if found
     */
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }
}