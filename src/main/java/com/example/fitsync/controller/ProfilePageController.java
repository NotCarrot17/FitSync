package com.example.fitsync.controller;

import com.example.fitsync.dao.ProfileDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfilePageController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private Button saveButton;

    private ProfileDAO profileDAO = new ProfileDAO();

    @FXML
    public void initialize() {
        // Load saved profile data
        nameField.setText(profileDAO.getProfile("name"));
        emailField.setText(profileDAO.getProfile("email"));
        ageField.setText(profileDAO.getProfile("age"));
        weightField.setText(profileDAO.getProfile("weight"));
        heightField.setText(profileDAO.getProfile("height"));
    }

    @FXML
    public void handleSaveProfile() {
        // Save profile data
        profileDAO.saveProfile("name", nameField.getText());
        profileDAO.saveProfile("email", emailField.getText());
        profileDAO.saveProfile("age", ageField.getText());
        profileDAO.saveProfile("weight", weightField.getText());
        profileDAO.saveProfile("height", heightField.getText());

        System.out.println("Profile data saved successfully.");
    }
}
