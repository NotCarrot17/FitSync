package com.example.fitsync.workoutJava;

import javafx.collections.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkoutSchedule {
    private static final ObservableList<ScheduledWorkout> schedule = FXCollections.observableArrayList();

    static {
        schedule.addAll(WorkoutFileManager.loadScheduledWorkouts());

        schedule.addListener((ListChangeListener<ScheduledWorkout>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    WorkoutFileManager.saveScheduledWorkouts(schedule);
                }
            }
        });
    }

    public static ObservableList<ScheduledWorkout> getSchedule() {
        return schedule;
    }

    public static void addWorkout(ScheduledWorkout workout) {
        schedule.add(workout);
    }
}

