package com.example.fitsync.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Popup;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private GridPane upcomingWorkoutsGrid;
    @FXML
    private Button btnSetStretch;

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
            } else if (clickedButton == btnStartNow) {
                fxmlFile = "/com/example/fxml/WorkoutExtraPages/StartNow.fxml";
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

                // Create an ImageView for the routine steps
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResourceAsStream("/WorkoutImages/StretchProcess.png")));
                imageView.setFitWidth(500); // Replace 200 with your desired width
                imageView.setPreserveRatio(true);

                // Create a label for the timer
                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                // Create a Cancel button
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                // Add the label and button to the layout
                popupLayout.getChildren().addAll(imageView,timerLabel, cancelButton);

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

                // Create an ImageView for the routine steps
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResourceAsStream("/WorkoutImages/BackStretchProcess.png")));
                imageView.setFitWidth(500); // Replace 200 with your desired width
                imageView.setPreserveRatio(true);

                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                popupLayout.getChildren().addAll(imageView, timerLabel, cancelButton);

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

                // Create an ImageView for the routine steps
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResourceAsStream("/WorkoutImages/YogaProcess.png")));
                imageView.setFitWidth(500); // Replace 200 with your desired width
                imageView.setPreserveRatio(true);

                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                popupLayout.getChildren().addAll(imageView, timerLabel, cancelButton);

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
            } else if (clickedButton == btnSetStretch) {
                Popup popup = new Popup();

                // Layout for popup content
                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1);
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; "
                        + "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                // DatePicker for date selection
                Label dateLabel = new Label("Select Date:");
                dateLabel.setStyle("-fx-font-size: 14px;");
                DatePicker datePicker = new DatePicker(LocalDate.now());

                // Time dropdown
                Label timeLabel = new Label("Select Time:");
                timeLabel.setStyle("-fx-font-size: 14px;");
                ComboBox<String> timeComboBox = new ComboBox<>();
                timeComboBox.setItems(FXCollections.observableArrayList(
                        "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM",
                        "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM",
                        "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM"
                ));
                timeComboBox.getSelectionModel().select("09:00 AM"); // Default time

                // OK and Cancel buttons
                Button okButton = new Button("OK");
                okButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ffa732; -fx-text-fill: white; -fx-padding: 2");
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                HBox buttonBox = new HBox(10); // 10px spacing between buttons
                buttonBox.setAlignment(Pos.CENTER);
                buttonBox.getChildren().addAll(okButton, cancelButton);;

                // Add components to layout
                popupLayout.getChildren().addAll(dateLabel, datePicker, timeLabel, timeComboBox, okButton, cancelButton);
                popup.getContent().add(popupLayout);

                // Handle OK button click
                okButton.setOnAction(e -> {
                    LocalDate selectedDate = datePicker.getValue();
                    String selectedTime = timeComboBox.getValue();

                    if (selectedDate != null && selectedTime != null) {
                        // Format date and time
                        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String workoutDetails = "Scheduled on " + formattedDate + " at " + selectedTime;

                        // Close popup
                        popup.hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select both a date and time.", ButtonType.OK);
                        alert.showAndWait();
                    }
                });

                // Handle Cancel button click
                cancelButton.setOnAction(e -> popup.hide());

                // Show popup
                if (!popup.isShowing()) {
                    popup.show(btnSetStretch.getScene().getWindow());
                }
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
