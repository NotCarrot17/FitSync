package com.example.fitsync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DietPlanController {
    // Navigation Buttons
    @FXML private Button btnOverview;
    @FXML private Button btnWorkout;
    @FXML private Button btnDietPlan;
    @FXML private Button btnGoalsAchievements;
    @FXML private Button btnProgress;
    @FXML private Button btnCommunity;
    @FXML private Button btnLogout;

    // Diet Plan Buttons
    @FXML private Button btnLogFood;
    @FXML private Button btnRecipes;

    // Input Fields
    @FXML private TextField foodField;
    @FXML private TextField caloriesField;
    @FXML private TextField proteinField;
    @FXML private TextField carbsField;
    @FXML private TextField fatsField;

    // Combo Box
    @FXML private ComboBox<String> mealTypeComboBox;

    // Text Area for displaying diet entries
    @FXML private TextArea dietEntriesArea;

    // Status Label
    @FXML private Label statusLabel;

    // List to store diet entries
    private ArrayList<Diet> dietEntries = new ArrayList<>();

    //Recommended Recipe Buttons
    @FXML
    private Button greenSaladButton;
    @FXML
    private Button roastedChickenButton;
    @FXML
    private Button oatsPancakesButton;

    // Initialize method
    @FXML
    private void initialize() {
        // Populate meal type combo box
        mealTypeComboBox.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snack");


        // Load initial data
        loadInitialData();
    }

    // Navigation Handler
    @FXML
    public void handleNavigation(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        // You can implement navigation logic here
        switch (buttonId) {
            case "btnOverview":
                // Navigate to Overview
                break;
            case "btnWorkout":
                // Navigate to Workout
                break;
            case "btnDietPlan":
                // Already on Diet Plan page
                break;
            case "btnGoalsAchievements":
                // Navigate to Goals
                break;
            case "btnProgress":
                // Navigate to Progress
                break;
            case "btnCommunity":
                // Navigate to Community
                break;
            case "btnLogout":
                // Logout functionality
                break;
        }
    }

    // Diet Plan Button Handler
    @FXML
    public void handleButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        switch (clickedButton.getId()) {
            case "btnLogFood":
                logFood();
                break;
            case "btnRecipes":
                showRecipes();
                break;
        }
    }

    // Log Food Method
    private void logFood() {
        try {
            // Validate inputs
            String food = validateInput(foodField, "Food");
            String mealType = validateComboBox();
            double calories = validateNumericInput(caloriesField, "Calories");
            double protein = validateNumericInput(proteinField, "Protein");
            double carbs = validateNumericInput(carbsField, "Carbohydrates");
            double fats = validateNumericInput(fatsField, "Fats");

            // Create new Diet object
            Diet newDiet = new Diet(food, mealType, calories, protein, carbs, fats);

            // Add to diet entries list
            dietEntries.add(newDiet);

            // Update display
            updateDietEntriesDisplay();

            // Clear input fields
            clearInputFields();

            // Show success message
            statusLabel.setText("Food logged successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            // Show error message
            statusLabel.setText(e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // Update Diet Entries Display
    private void updateDietEntriesDisplay() {
        StringBuilder display = new StringBuilder();
        for (Diet diet : dietEntries) {
            display.append(String.format("Food: %s, Meal: %s, Calories: %.1f, Protein: %.1f, Carbs: %.1f, Fats: %.1f\n",
                    diet.getFood(), diet.getMealType(), diet.getCalories(),
                    diet.getProtein(), diet.getCarbs(), diet.getFats()));
        }
        dietEntriesArea.setText(display.toString());
    }

    // Show Recipes Method
    private void showRecipes() {
        statusLabel.setText("Recipes feature coming soon!");
    }

    // Validation Methods
    private String validateInput(TextField field, String fieldName) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value;
    }

    private double validateNumericInput(TextField field, String fieldName) {
        String value = field.getText().trim();
        try {
            double numValue = Double.parseDouble(value);
            if (numValue < 0) {
                throw new IllegalArgumentException(fieldName + " cannot be negative");
            }
            return numValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName);
        }
    }

    private String validateComboBox() {
        String mealType = mealTypeComboBox.getValue();
        if (mealType == null || mealType.isEmpty()) {
            throw new IllegalArgumentException("Please select a meal type");
        }
        return mealType;
    }

    // Clear Input Fields
    private void clearInputFields() {
        foodField.clear();
        caloriesField.clear();
        proteinField.clear();
        carbsField.clear();
        fatsField.clear();
        mealTypeComboBox.setValue(null);
    }

    // Load Initial Data Method
    private void loadInitialData() {
        dietEntries.add(new Diet("Chicken Breast", "Lunch", 165, 31, 0, 3.6));
        dietEntries.add(new Diet("Salad", "Dinner", 220, 7.45, 44, 6.63));
        dietEntries.add(new Diet("Oatmeal", "Breakfast", 150, 12.48, 17.35, 3.80));
        dietEntries.add(new Diet("Yogurt", "Snack", 100, 12.89, 6, 0));
        dietEntries.add(new Diet("Steak", "Dinner", 840, 46, 0, 72));
        dietEntries.add(new Diet("Mixed Nuts", "Snack", 200, 25.80, 16.13, 49.24));
        dietEntries.add(new Diet("Salmon", "Lunch", 177, 18.80, 0, 10.50));

        // Update display with initial entries
        updateDietEntriesDisplay();
    }

    // Diet Class (Inner Class)
    public static class Diet {
        private String food;
        private String mealType;
        private double calories;
        private double protein;
        private double carbs;
        private double fats;

        // Constructor
        public Diet(String food, String mealType, double calories,
                    double protein, double carbs, double fats) {
            this.food = food;
            this.mealType = mealType;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fats = fats;
        }

        // Getters
        public String getFood() { return food; }
        public String getMealType() { return mealType; }
        public double getCalories() { return calories; }
        public double getProtein() { return protein; }
        public double getCarbs() { return carbs; }
        public double getFats() { return fats; }
    }



}