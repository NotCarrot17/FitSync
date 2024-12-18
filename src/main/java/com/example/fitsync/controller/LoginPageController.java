package com.example.fitsync.controller;

import com.example.fitsync.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LoginPageController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPasswordButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button languageButton;
    @FXML
    private Button notificationButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Label errorMessage;
    @FXML
    private VBox notificationPopup;
    @FXML
    private Button googleSignInButton;
    @FXML
    private Button facebookSignInButton;


    private UserDAO userDAO = new UserDAO();


    @FXML
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            if (userDAO.authenticate(email, password)) {
                System.out.println("Login successful. Navigate to Dashboard.");
                errorMessage.setText(""); // Clear any previous error message
            }
        } catch (UserDAO.AuthenticationException ex) {
            String errorMsg = "Authentication failed: " + ex.getMessage();
            errorMessage.setText(errorMsg);
            errorMessage.setVisible(true);

            // Display the error message in the main console
            System.out.println(errorMsg);
        }
    }

    @FXML
    public void handleForgotPassword(ActionEvent event) {
        String email = emailField.getText();
            if (userDAO.emailExists(email)) {
                System.out.println("Password reset link sent to " + email);
            } else {
                errorMessage.setText("Email not found. Please register.");
                errorMessage.setVisible(true);
            }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Check email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            System.out.println("Invalid email format. Please use a valid email address.");
            return;
        }

        if (userDAO.registerUser(email, password)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Registration failed. Email may already be registered.");
        }
    }

    @FXML
    public void changeLanguage(ActionEvent event) {
        // Placeholder for Google Cloud Translation API integration
        System.out.println("Language changed.");
    }

    @FXML
    public void showNotifications(ActionEvent event) {
        // Show notification popup
        notificationPopup.setVisible(true);
    }

    @FXML
    public void closeNotifications(ActionEvent event) {
        // Close notification popup
        notificationPopup.setVisible(false);
    }

    @FXML
    public void openSettings(ActionEvent event) {
        // Placeholder for opening settings page
        System.out.println("Opening settings page.");
    }

    @FXML
    public void handleGoogleSignIn(ActionEvent event) {
        // Placeholder for Google Sign-In logic
        System.out.println("Google Sign-In clicked.");
    }

    @FXML
    public void handleFacebookSignIn(ActionEvent event) {
        // Placeholder for Facebook Sign-In logic
        System.out.println("Facebook Sign-In clicked.");
    }
}