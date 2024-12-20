package com.example.fitsync.controller;

import com.example.fitsync.workoutJava.ScheduledWorkout;
import com.example.fitsync.workoutJava.WorkoutClass;
import com.example.fitsync.workoutJava.WorkoutData;
import com.example.fitsync.workoutJava.WorkoutSchedule;
import com.example.fitsync.workoutJava.WorkoutFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Button btnSetStretch;
    @FXML
    private VBox purchasedClassesContainer;
    @FXML
    private VBox scheduleContainer;

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
                fxmlFile = "com/example/fxml/DietPlan.fxml";
            } else if (clickedButton == btnGoalsAchievements) {
                fxmlFile = "/com/example/fitsync/view/GoalsAchievementsPage.fxml";
            } else if (clickedButton == btnProgress) {
                fxmlFile = "/com/example/fitsync/view/ProgressPage.fxml";
            } else if (clickedButton == btnCommunity) {
                fxmlFile = "/com/example/fitsync/view/CommunityPage.fxml";
            } else if (clickedButton == btnLogout) {
                fxmlFile = "/com/example/fitsync/view/LoginPage.fxml";
            }

            if (fxmlFile != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                Stage stage = (Stage) clickedButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        WorkoutData.getPurchasedClasses().addListener((ListChangeListener<WorkoutClass>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (WorkoutClass workoutClass : change.getAddedSubList()) {
                        addPurchasedClassToUI(workoutClass);
                    }
                }
            }
        });

        WorkoutSchedule.getSchedule().addListener((ListChangeListener<ScheduledWorkout>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (ScheduledWorkout workout : change.getAddedSubList()) {
                        addScheduledWorkoutToUI(workout);
                    }
                }
            }
        });

        reloadPurchasedClasses();
        reloadSchedule();
    }

    private void reloadPurchasedClasses() {
        purchasedClassesContainer.getChildren().clear();
        for (WorkoutClass workoutClass : WorkoutData.getPurchasedClasses()) {
            addPurchasedClassToUI(workoutClass);
        }
    }

    private void reloadSchedule() {
        scheduleContainer.getChildren().clear();
        for (ScheduledWorkout workout : WorkoutSchedule.getSchedule()) {
            addScheduledWorkoutToUI(workout);
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
                WorkoutData.addPurchasedClass(new WorkoutClass("UpperBody Workout", "RM120"));
            } else if (clickedButton == btnCoachVideo) {
                Stage videoStage = new Stage();
                videoStage.setTitle("YouTube Video");

                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();
                webEngine.load("https://www.youtube.com/watch?v=uVwNVEQS_uo");

                Scene scene = new Scene(webView, 800, 450);

                videoStage.setScene(scene);
                videoStage.show();
            } else if (clickedButton == btnStretch) {
                Popup popup = new Popup();
                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1); // Arrange items vertically
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResourceAsStream("/WorkoutImages/StretchProcess.png")));
                imageView.setFitWidth(500);
                imageView.setPreserveRatio(true);

                Label timerLabel = new Label("Time Remaining: 05:00");
                timerLabel.setStyle("-fx-font-size: 14px;");

                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                popupLayout.getChildren().addAll(imageView,timerLabel, cancelButton);

                popup.getContent().add(popupLayout);

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

                TilePane popupLayout = new TilePane();
                popupLayout.setPrefColumns(1);
                popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-gap: 10; "
                        + "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                Label dateLabel = new Label("Select Date:");
                dateLabel.setStyle("-fx-font-size: 14px;");
                DatePicker datePicker = new DatePicker(LocalDate.now());

                Label timeLabel = new Label("Select Time:");
                timeLabel.setStyle("-fx-font-size: 14px;");
                ComboBox<String> timeComboBox = new ComboBox<>();
                timeComboBox.setItems(FXCollections.observableArrayList(
                        "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM",
                        "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM",
                        "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM"
                ));
                timeComboBox.getSelectionModel().select("09:00 AM");

                Button okButton = new Button("OK");
                okButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ffa732; -fx-text-fill: white;");
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ef4040; -fx-text-fill: white;");

                HBox buttonBox = new HBox(10);
                buttonBox.setAlignment(Pos.CENTER);
                buttonBox.getChildren().addAll(okButton, cancelButton);

                popupLayout.getChildren().addAll(dateLabel, datePicker, timeLabel, timeComboBox, okButton, cancelButton);
                popup.getContent().add(popupLayout);

                okButton.setOnAction(e -> {
                    LocalDate selectedDate = datePicker.getValue();
                    String selectedTime = timeComboBox.getValue();

                    if (selectedDate != null && selectedTime != null) {
                        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        WorkoutSchedule.addWorkout(new ScheduledWorkout("Stretch", formattedDate, selectedTime));
                        popup.hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select both a date and time.", ButtonType.OK);
                        alert.showAndWait();
                    }
                });

                cancelButton.setOnAction(e -> popup.hide());

                if (!popup.isShowing()) {
                    popup.show(btnSetStretch.getScene().getWindow());
                }
            }
            if (fxmlFile != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                Stage stage = (Stage) clickedButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPurchasedClassToUI(WorkoutClass workoutClass) {
        HBox workoutBox = new HBox();
        workoutBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        workoutBox.setSpacing(10);

        VBox detailsBox = new VBox();
        Label className = new Label(workoutClass.getName());
        Label price = new Label(workoutClass.getPrice());
        price.setStyle("-fx-text-fill: #9d9d9d; -fx-font-size: 9px;");
        detailsBox.getChildren().addAll(className, price);

        workoutBox.getChildren().addAll(detailsBox);
        purchasedClassesContainer.getChildren().add(workoutBox);
    }

    private void addScheduledWorkoutToUI(ScheduledWorkout workout) {
        HBox scheduleBox = new HBox();
        scheduleBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        scheduleBox.setSpacing(2);

        Label dateLabel = new Label(workout.getDate());
        dateLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #9d9d9d;");
        dateLabel.setPrefWidth(96);

        VBox detailsBox = new VBox();
        Label workoutLabel = new Label(workout.getName());
        Label timeLabel = new Label("AT " + workout.getTime());
        timeLabel.setStyle("-fx-text-fill: #9d9d9d; -fx-font-size: 9px;");
        detailsBox.getChildren().addAll(workoutLabel, timeLabel);

        scheduleBox.getChildren().addAll(dateLabel, detailsBox);
        scheduleContainer.getChildren().add(scheduleBox);
    }
}