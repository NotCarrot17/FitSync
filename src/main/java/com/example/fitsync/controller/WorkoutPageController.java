package com.example.fitsync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.layout.TilePane;

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
    private Button btnStretch;
    @FXML
    private Button btnBackStretch;
    @FXML
    private Button btnYoga;

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
            } else if (clickedButton == goBack) {
                fxmlFile = "/com/example/fxml/WorkoutPage.fxml";
            } else if (clickedButton == btnRoutines) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/Routines.fxml";
            } else if (clickedButton == buyNow) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Order Confirmation");
                alert.setContentText("Congratulations!!! Purchase Successful!!!");
                alert.showAndWait();
            } else if (clickedButton == btnCoachVideo) {
                // Create a new Stage (Window)
                Stage videoStage = new Stage();
                videoStage.setTitle("YouTube Video");

                // Create a WebView and load the YouTube video
                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();
                webEngine.load("https://www.youtube.com/watch?v=uVwNVEQS_uo");

                // Create a Scene with the WebView
                Scene scene = new Scene(webView, 800, 450);

                // Set the scene on the new stage
                videoStage.setScene(scene);
                videoStage.show();
            } else if (clickedButton == btnStretch) {
                // Create a popup
                Popup popup = new Popup();

                // Create a layout for the popup content
                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1); // Arrange items vertically
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                // Create a label for the timer
                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                // Create a Cancel button
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                // Add the label and button to the layout
                popupLayout.getChildren().addAll(timerLabel, cancelButton);

                // Add the layout to the popup
                popup.getContent().add(popupLayout);

                // Variables for countdown
                int[] timeRemaining = {300}; // 5 minutes in seconds

                // Declare the Timeline variable
                Timeline timeline = new Timeline();

                // Add the KeyFrame to the timeline
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                int minutes = timeRemaining[0] / 60;
                                int seconds = timeRemaining[0] % 60;
                                timerLabel.setText(String.format("Time Remaining: %02d:%02d", minutes, seconds));

                                // Decrement the time
                                timeRemaining[0]--;

                                // Stop the timer when it reaches 0
                                if (timeRemaining[0] < 0) {
                                    timeline.stop(); // Access timeline here without issues
                                    timerLabel.setText("Time's Up!");
                                }
                            }
                        })
                );

                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                // Show the popup and start the timer
                if (!popup.isShowing()) {
                    popup.show(clickedButton.getScene().getWindow());
                    timeline.play();
                }

                // Add action to Cancel button to close the popup and stop the timer
                cancelButton.setOnAction(e -> {
                    timeline.stop();
                    popup.hide();
                });
            } else if (clickedButton == btnBackStretch) {
                Popup popup = new Popup();

                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1);
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                popupLayout.getChildren().addAll(timerLabel, cancelButton);

                popup.getContent().add(popupLayout);

                // Variables for countdown
                int[] timeRemaining = {300}; // 5 minutes in seconds

                Timeline timeline = new Timeline();

                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                int minutes = timeRemaining[0] / 60;
                                int seconds = timeRemaining[0] % 60;
                                timerLabel.setText(String.format("Time Remaining: %02d:%02d", minutes, seconds));

                                timeRemaining[0]--;

                                if (timeRemaining[0] < 0) {
                                    timeline.stop();
                                    timerLabel.setText("Time's Up!");
                                }
                            }
                        })
                );

                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                if (!popup.isShowing()) {
                    popup.show(clickedButton.getScene().getWindow());
                    timeline.play();
                }

                cancelButton.setOnAction(e -> {
                    timeline.stop();
                    popup.hide();
                });
            } else if (clickedButton == btnYoga) {

                Popup popup = new Popup();

                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1);
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                popupLayout.getChildren().addAll(timerLabel, cancelButton);

                popup.getContent().add(popupLayout);

                int[] timeRemaining = {300};

                Timeline timeline = new Timeline();

                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                int minutes = timeRemaining[0] / 60;
                                int seconds = timeRemaining[0] % 60;
                                timerLabel.setText(String.format("Time Remaining: %02d:%02d", minutes, seconds));

                                timeRemaining[0]--;

                                if (timeRemaining[0] < 0) {
                                    timeline.stop();
                                    timerLabel.setText("Time's Up!");
                                }
                            }
                        })
                );

                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                if (!popup.isShowing()) {
                    popup.show(clickedButton.getScene().getWindow());
                    timeline.play();
                }

                cancelButton.setOnAction(e -> {
                    timeline.stop();
                    popup.hide();
                });
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