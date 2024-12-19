package com.example.fitsync.controller;

import com.example.fitsync.dao.ProfileDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;

import java.util.HashMap;
import java.util.Map;

public class ProfilePageController {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField genderField;
    @FXML
    private DatePicker dobField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Button saveButton;
    @FXML
    private Label statusMessage;

    private ProfileDAO profileDAO = new ProfileDAO();

    @FXML
    public void handleSave(ActionEvent event) {
        // Collect data from text fields
        Map<String, String> profileData = new HashMap<>();
        profileData.put("Full Name", fullNameField.getText());
        profileData.put("Phone Number", phoneField.getText());
        profileData.put("Email", emailField.getText());
        profileData.put("Password", passwordField.getText());
        profileData.put("Gender", genderField.getText());
        profileData.put("Date of Birth", dobField.getValue() != null ? dobField.getValue().toString() : "N/A");
        profileData.put("Weight", weightField.getText());
        profileData.put("Height", heightField.getText());

        // Save profile data to a file
        profileDAO.saveProfileData(profileData);

        // Show confirmation message
        statusMessage.setText("Profile saved successfully!");
        statusMessage.setVisible(true);
    }
}
