package com.example.fitsync.controller;

import javafx.application.Application;
import com.example.fitsync.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class FitnessTrackerController{
    @FXML
    private Button btnOverView;
    @FXML
    private Button btnWorkout;
    @FXML
    private Button btnDietPlan;
    @FXML
    private Button btnFitnessTracker;
    @FXML
    private Button btnGoalsAchievements;
    @FXML
    private Button btnCommunity;
    @FXML
    private Button btnLogout;

    @FXML
    private void handleNavigation(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            String fxmlFile = null;

            if (clickedButton == btnOverView) {
                fxmlFile = "/com/example/fitsync/view/OverviewPage.fxml";
            } else if (clickedButton == btnWorkout) {
                fxmlFile = "/com/example/fitsync/view/WorkoutPage.fxml";
            } else if (clickedButton == btnDietPlan) {
                fxmlFile = "/com/example/fitsync/view/DietPlanPage.fxml";
            } else if (clickedButton == btnGoalsAchievements) {
                fxmlFile = "/com/example/fitsync/view/GoalsAchievementsPage.fxml";
            }else if (clickedButton == btnCommunity) {
                fxmlFile = "/com/example/fitsync/view/CommunityPage.fxml";
            } else if (clickedButton == btnLogout) {
                fxmlFile = "/com/example/fitsync/view/LoginPage.fxml";
            }

            // Load the FXML file
            if (fxmlFile != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                // Get the current stage and set the new scene
                Stage stage = (Stage) clickedButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
