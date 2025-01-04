package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing HTTP requests related to training sessions.
 */

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Deletes a training session by its ID.
     *
     * @param id the unique identifier of the training session to be deleted
     * @return a response indicating successful deletion
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Creates a new training session.
     *
     * @param trainingDto the DTO representing the training session to be created
     * @return the DTO of the newly created training session
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDtoWithUserId trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Fetches a list of all training sessions.
     *
     * @return a list of DTOs representing all training sessions
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves training sessions associated with a specific user ID.
     *
     * @param userId the ID of the user whose trainings are to be fetched
     * @return a list of DTOs for the user's training sessions
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingByUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Fetches training sessions completed after a specific date.
     *
     * @param afterTime the date after which completed trainings should be retrieved
     * @return a list of DTOs for the completed training sessions
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainings(@PathVariable("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.findFinishedTrainings(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Fetches training sessions by their activity type.
     *
     * @param activityType the type of activity to filter the trainings
     * @return a list of DTOs representing trainings of the specified activity type
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Updates an entire training session by its ID.
     *
     * @param id          the ID of the training session to update
     * @param trainingDto the DTO containing updated information for the training session
     * @return the DTO of the updated training session
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDtoWithUserId trainingDto) {
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Partially updates a training session by its ID.
     * Allows for updating specific fields without modifying the entire session.
     *
     * @param id      the ID of the training session to update
     * @param updates a map of fields to update and their new values
     * @return the DTO of the partially updated training session
     */
    @PatchMapping("/{id}")
    public TrainingDto partiallyUpdateTraining(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        Training updatedTraining = trainingService.partiallyUpdateTraining(id, updates);
        return trainingMapper.toDto(updatedTraining);
    }
}
