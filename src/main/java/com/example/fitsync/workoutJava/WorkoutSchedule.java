package com.example.fitsync.workoutJava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkoutSchedule {
    private static final ObservableList<ScheduledWorkout> schedule = FXCollections.observableArrayList();

    static {
        schedule.add(new ScheduledWorkout("Stretch", "2024-12-26", "09:00 AM"));
        schedule.add(new ScheduledWorkout("Back Stretch", "2024-12-27", "10:00 AM"));
        schedule.add(new ScheduledWorkout("Yoga", "2025-12-28", "11:00 AM"));
    }

    public static ObservableList<ScheduledWorkout> getSchedule() {
        return schedule;
    }

    public static void addWorkout(ScheduledWorkout workout) {
        schedule.add(workout);
    }
}
