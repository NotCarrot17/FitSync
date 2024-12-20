package com.example.fitsync.workoutJava;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutFileManager {

    private static final String PURCHASED_CLASSES_FILE = "purchased_classes.txt";
    private static final String SCHEDULED_WORKOUTS_FILE = "scheduled_workouts.txt";

    public static void savePurchasedClasses(List<WorkoutClass> classes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PURCHASED_CLASSES_FILE))) {
            for (WorkoutClass workoutClass : classes) {
                writer.write(workoutClass.getName() + "," + workoutClass.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<WorkoutClass> loadPurchasedClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PURCHASED_CLASSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    classes.add(new WorkoutClass(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static void saveScheduledWorkouts(List<ScheduledWorkout> workouts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCHEDULED_WORKOUTS_FILE))) {
            for (ScheduledWorkout workout : workouts) {
                writer.write(workout.getName() + "," + workout.getDate() + "," + workout.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ScheduledWorkout> loadScheduledWorkouts() {
        List<ScheduledWorkout> workouts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEDULED_WORKOUTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    workouts.add(new ScheduledWorkout(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workouts;
    }
}
