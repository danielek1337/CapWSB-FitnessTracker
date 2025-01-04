package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Entity
@Table(name = "trainings")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString

/**
 * Creates a new training session with the specified parameters.
 *
 * @param user         the user associated with this training session
 * @param startTime    the starting time of the training session
 * @param activityType the type of activity performed during the session
 * @param distance     the total distance covered during the session
 * @param endTime      the ending time of the training session
 * @param averageSpeed the average speed achieved during the session
 */


public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}