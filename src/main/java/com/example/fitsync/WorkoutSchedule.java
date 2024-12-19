package com.example.fitsync;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkoutSchedule {
    private static final ObservableList<ScheduledWorkout> schedule = FXCollections.observableArrayList();

    static {
        // Add initial schedule items
        schedule.add(new ScheduledWorkout("Stretch", "2025-01-01", "08:00 AM"));
        schedule.add(new ScheduledWorkout("Back Stretch", "2025-01-02", "08:00 AM"));
        schedule.add(new ScheduledWorkout("Yoga", "2025-01-03", "09:00 AM"));
    }

    public static ObservableList<ScheduledWorkout> getSchedule() {
        return schedule;
    }

    public static void addWorkout(ScheduledWorkout workout) {
        schedule.add(workout);
    }
}
