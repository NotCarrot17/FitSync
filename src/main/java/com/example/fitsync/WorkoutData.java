package com.example.fitsync;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkoutData {
    private static final ObservableList<WorkoutClass> purchasedClasses = FXCollections.observableArrayList();

    static {
        // Add initial data here
        purchasedClasses.add(new WorkoutClass("Yoga Class", "RM100"));
        purchasedClasses.add(new WorkoutClass("Swimming Class", "RM100"));
    }

    public static ObservableList<WorkoutClass> getPurchasedClasses() {
        return purchasedClasses;
    }

    public static void addPurchasedClass(WorkoutClass workoutClass) {
        purchasedClasses.add(workoutClass);
    }
}
