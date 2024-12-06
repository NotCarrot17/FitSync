package com.example.fitsync.dao;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    // Temporary storage for user data (emulating local database)
    private Map<String, String> users = new HashMap<>();


    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    // Check if the user credentials are valid
    public boolean isValidUser(String email, String password) throws AuthenticationException {
        if (!users.containsKey(email)) {
            throw new AuthenticationException("Email does not exist.");
        }
        if (!users.get(email).equals(password)) {
            throw new AuthenticationException("Invalid password.");
        }
        return true;
    }

    public void authenticate(String email, String password) {
        try {
            if (isValidUser(email, password)) {
                System.out.println("User authenticated successfully.");
            }
        } catch (AuthenticationException e) {
            System.err.println("Authentication failed: " + e.getMessage());
        }
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

