package com.capgemini.wsb.fitnesstracker.training.internal;
/**
 * Various types of activities defined by ActivityType enum
 */

public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    /**
     * Display activity type name
     */
    private final String displayName;

    /**
     * Initializes an activity type with the given display name.
     *
     * @param displayName activity type name
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the name of the activity type.
     *
     * @return the display name connected to the activity type
     */
    public String getDisplayName() {
        return displayName;
    }

}
