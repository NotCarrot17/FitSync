package com.example.fitsync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

public class DietPlanController {
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnWorkout;
    @FXML
    private Button btnDietPlan;
    @FXML
    private Button btnGoalsAchievements;
    @FXML
    private Button btnProgress;
    @FXML
    private Button btnCommunity;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnLogFood;

    @FXML
    private TextField foodField;
    @FXML
    private TextField caloriesField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField carbsField;
    @FXML
    private TextField fatsField;

    @FXML
    private ComboBox<String> mealTypeComboBox;
    @FXML
    private TextArea dietEntriesArea;
    private ArrayList<Diet> dietEntries = new ArrayList<>();
    @FXML
    private Label statusLabel;

    @FXML
    private ProgressBar waterProgressBar;
    @FXML
    private Label waterAmountLabel;

    private double waterIntake = 0.25;
    private double waterTarget = 3;

    @FXML
    private void initialize() {
        mealTypeComboBox.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snack");
        updateWaterProgress();
        loadInitialData();
    }

    @FXML
    private void addWater() {
        waterIntake += 0.25;
        updateWaterProgress();
    }

    private void updateWaterProgress() {
        waterProgressBar.setProgress(waterIntake / waterTarget);
        waterAmountLabel.setText(String.format("%.2f Liters", waterIntake));
    }


    // Navigation Handler
    @FXML
    public void handleNavigation(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        switch (buttonId) {
            case "btnOverview":
                break;
            case "btnWorkout":
                break;
            case "btnDietPlan":
                break;
            case "btnGoalsAchievements":
                break;
            case "btnProgress":
                break;
            case "btnCommunity":
                break;
            case "btnLogout":
                break;
        }
    }


    @FXML
    public void handleButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        switch (clickedButton.getId()) {
            case "btnLogFood":
                logFood();
                break;
        }
    }

    private void logFood() {
        try {
            String mealType = validateComboBox();
            String food = validateInput(foodField, "Food");
            double calories = validateNumericInput(caloriesField, "Calories");
            double protein = validateNumericInput(proteinField, "Protein");
            double carbs = validateNumericInput(carbsField, "Carbohydrates");
            double fats = validateNumericInput(fatsField, "Fats");

            Diet newDiet = new Diet(food, mealType, calories, protein, carbs, fats);

            dietEntries.add(newDiet);
            updateDietEntriesDisplay();
            clearInputFields();
            statusLabel.setText("Food logged successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void updateDietEntriesDisplay() {
        StringBuilder display = new StringBuilder();
        for (Diet diet : dietEntries) {
            display.append(String.format("Food: %s, Meal: %s, Calories: %.1f, Protein: %.1f, Carbs: %.1f, Fats: %.1f\n",
                    diet.getFood(), diet.getMealType(), diet.getCalories(),
                    diet.getProtein(), diet.getCarbs(), diet.getFats()));
        }
        dietEntriesArea.setText(display.toString());
    }

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

    private void clearInputFields() {
        foodField.clear();
        caloriesField.clear();
        proteinField.clear();
        carbsField.clear();
        fatsField.clear();
        mealTypeComboBox.setValue(null);
    }

    private void loadInitialData() {
        dietEntries.add(new Diet("Chicken Breast", "Lunch", 165, 31, 0, 3.6));
        dietEntries.add(new Diet("Salad", "Dinner", 220, 7.45, 44, 6.63));
        dietEntries.add(new Diet("Oatmeal", "Breakfast", 150, 12.48, 17.35, 3.80));
        dietEntries.add(new Diet("Yogurt", "Snack", 100, 12.89, 6, 0));
        dietEntries.add(new Diet("Steak", "Dinner", 840, 46, 0, 72));
        dietEntries.add(new Diet("Mixed Nuts", "Snack", 200, 25.80, 16.13, 49.24));
        dietEntries.add(new Diet("Salmon", "Lunch", 177, 18.80, 0, 10.50));
        updateDietEntriesDisplay();
    }

    public static class Diet {
        private String food;
        private String mealType;
        private double calories;
        private double protein;
        private double carbs;
        private double fats;

        public Diet(String food, String mealType, double calories,
                    double protein, double carbs, double fats)
        {
            this.food = food;
            this.mealType = mealType;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fats = fats;
        }

        public String getFood() {
            return food;
        }
        public String getMealType() {
            return mealType;
        }
        public double getCalories() {
            return calories;
        }
        public double getProtein() {
            return protein;
        }
        public double getCarbs() {
            return carbs;
        }
        public double getFats() {
            return fats;
        }
    }

}