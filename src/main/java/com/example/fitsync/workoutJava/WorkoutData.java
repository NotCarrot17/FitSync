package com.example.fitsync.workoutJava;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class WorkoutData {
    private static final ObservableList<WorkoutClass> purchasedClasses = FXCollections.observableArrayList();

    static {
        purchasedClasses.addAll(WorkoutFileManager.loadPurchasedClasses());

        purchasedClasses.addListener((ListChangeListener<WorkoutClass>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    WorkoutFileManager.savePurchasedClasses(purchasedClasses);
                }
            }
        });
    }

    public static ObservableList<WorkoutClass> getPurchasedClasses() {
        return purchasedClasses;
    }

    public static void addPurchasedClass(WorkoutClass workoutClass) {
        purchasedClasses.add(workoutClass);
    }
}


