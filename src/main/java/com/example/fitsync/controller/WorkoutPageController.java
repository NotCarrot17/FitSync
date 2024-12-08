package com.example.fitsync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkoutPageController {

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
    private Button btnRoutines;
    @FXML
    private Button btnStartNow;
    @FXML
    private Button btnCoachVideo;
    @FXML
    private Button btnUpperbodyWorkout;
    @FXML
    private Button btnZumba;
    @FXML
    private Button btnPilates;
    @FXML
    private Button goBack;
    @FXML
    private Button buyNow;

    @FXML
    private void handleNavigation(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            String fxmlFile = null;

            if (clickedButton == btnOverview) {
                fxmlFile = "/com/example/fitsync/view/OverviewPage.fxml";
            } else if (clickedButton == btnWorkout) {
                fxmlFile = "/com/example/fitsync/view/WorkoutPage.fxml";
            } else if (clickedButton == btnDietPlan) {
                fxmlFile = "/com/example/fitsync/view/DietPlanPage.fxml";
            } else if (clickedButton == btnGoalsAchievements) {
                fxmlFile = "/com/example/fitsync/view/GoalsAchievementsPage.fxml";
            } else if (clickedButton == btnProgress) {
                fxmlFile = "/com/example/fitsync/view/ProgressPage.fxml";
            } else if (clickedButton == btnCommunity) {
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

    @FXML
    private void handleButtons(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            String fxmlFile = null;

            if (clickedButton == btnUpperbodyWorkout) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/UpperbodyWorkout.fxml";
            } else if (clickedButton == btnZumba) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/Zumba.fxml";
            } else if (clickedButton == btnPilates) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/Pilates.fxml";
            } else if (clickedButton == btnStartNow) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/StartNow.fxml";
            } else if (clickedButton == btnRoutines) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/Routines.fxml";
            } else if (clickedButton == btnCoachVideo) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/CoachVideo.fxml";
            } else if (clickedButton == goBack) {
                fxmlFile = "/com/example/fxml/WorkoutPage.fxml";
            } else if (clickedButton == buyNow) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Order Confirmation");
                alert.setContentText("Congratulations!!! Purchase Successful!!!");
                alert.showAndWait();
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

//    @FXML
//    private void handleBuyNow(ActionEvent event) {
//        Button clickedButton = (Button) event.getSource();
//        String fxmlFile = null;
//
//        if (clickedButton == buyNow) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Confirmation");
//            alert.setHeaderText("Order Confirmation");
//            alert.setContentText("Congratulations!!! Purchase Successful!!!");
//            alert.showAndWait();
//        }
//    }



}
