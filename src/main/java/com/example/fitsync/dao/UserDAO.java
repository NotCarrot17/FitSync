package com.example.fitsync.dao;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    // Temporary storage for user data (emulating local database)
    private Map<String, String> users = new HashMap<>();

    public UserDAO() {
        // Dummy data for testing
        users.put("test@example.com", "password123");
    }

    // Check if the user credentials are valid
    public boolean isValidUser(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return users.containsKey(email);
    }

    // Register new user
    public boolean registerUser(String email, String password) {
        if (!users.containsKey(email)) {
            users.put(email, password);
            return true;
        }
        return false;
    }
}
