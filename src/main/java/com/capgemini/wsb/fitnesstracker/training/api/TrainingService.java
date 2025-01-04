package com.capgemini.wsb.fitnesstracker.training.api;
import java.util.Map;
/**
 * API interface for performing modification operations on {@link Training} entities.
 * Implementations handle database transactions, either by utilizing an existing transaction or initiating a new one as necessary.
 */

public interface TrainingService {
    /**
     * Deletes a training session by its ID.
     *
     * @param id the unique identifier of the training session to be removed
     */
    void deleteTraining(Long id);

    /**
     * Creates a new training session in the system.
     *
     * @param training the training entity to be saved
     * @return the newly created training entity
     */
    Training createTraining(Training training);

    /**
     * Updates the details of an existing training session.
     *
     * @param id the unique identifier of the training session to be updated
     * @param training the training entity containing updated information
     * @return the training entity after the update
     */
    Training updateTraining(Long id, Training training);

    /**
     * Applies partial updates to an existing training session.
     * This method allows modifying specific fields without altering the entire entity.
     *
     * @param id the unique identifier of the training session to be updated
     * @param updates a map containing field names as keys and their new values
     * @return the training entity after the partial update
     */
    Training partiallyUpdateTraining(Long id, Map<String, Object> updates);

}
