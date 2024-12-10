package com.example.fitsync;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main Layout
        TabPane tabPane = new TabPane();

        // Tabs for different pages
        Tab loginTab = new Tab("Login", createLoginPage());
        Tab profileTab = new Tab("Profile", createProfilePage());
        Tab dashboardTab = new Tab("Dashboard", createDashboardPage());
        Tab workoutTab = new Tab("Workout", createWorkoutPage());
        Tab dietTab = new Tab("Diet Plan", createDietPlanPage());
        Tab trackerTab = new Tab("Fitness Tracker", createTrackerPage());
        Tab goalsTab = new Tab("Goals & Achievements", createGoalsPage());
        Tab communityTab = new Tab("Community", createCommunityPage());

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(
                loginTab, profileTab, dashboardTab, workoutTab, dietTab,
                trackerTab, goalsTab, communityTab
        );

        // Set tabs to not closable
        tabPane.getTabs().forEach(tab -> tab.setClosable(false));

        // Main Scene
        Scene scene = new Scene(tabPane, 800, 600);

        primaryStage.setTitle("FitSync Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createLoginPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createProfilePage() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        layout.getChildren().addAll(nameLabel, nameField, phoneLabel, phoneField, emailLabel, emailField, passwordLabel, passwordField);

        return layout;
    }

    private Pane createDashboardPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Pane createWorkoutPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/WorkoutPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createDietPlanPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createTrackerPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createGoalsPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createCommunityPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LoginPage.fxml"));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}