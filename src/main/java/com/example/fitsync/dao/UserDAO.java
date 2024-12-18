package com.example.fitsync.dao;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO implements Serializable {
    // File to store user data
    private static final String USER_FILE = "users.ser";

    // In-memory storage of users
    private Map<String, UserData> users;

    // Constructor
    public UserDAO() {
        // Load existing users or initialize a new map
        users = loadUsers();
    }

    // User data class (serializable)
    private static class UserData implements Serializable {
        private String email;
        private String hashedPassword;
        private long createdAt;

        public UserData(String email, String hashedPassword) {
            this.email = email;
            this.hashedPassword = hashedPassword;
            this.createdAt = System.currentTimeMillis();
        }
    }

    // Custom Authentication Exception
    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    // Hash password for secure storage
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save users to file
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users from file
    @SuppressWarnings("unchecked")
    private Map<String, UserData> loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, UserData>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // Check if the user credentials are valid
    public boolean isValidUser(String email, String password) throws AuthenticationException {
        String hashedPassword = hashPassword(password);

        UserData userData = users.get(email);
        if (userData == null) {
            throw new AuthenticationException("Email does not exist.");
        }

        if (!userData.hashedPassword.equals(hashedPassword)) {
            throw new AuthenticationException("Invalid password.");
        }

        return true;
    }

    // Authenticate user
    public boolean authenticate(String email, String password) throws AuthenticationException {
        return isValidUser(email, password);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return users.containsKey(email);
    }

    // Register new user
    public boolean registerUser(String email, String password) {
        // Basic email validation
        if (!isValidEmailFormat(email)) {
            return false;
        }

        // Check if user already exists
        if (emailExists(email)) {
            return false;
        }

        // Hash password
        String hashedPassword = hashPassword(password);

        // Create and store user data
        users.put(email, new UserData(email, hashedPassword));

        // Save to file
        saveUsers();

        return true;
    }

    // Basic email format validation
    private boolean isValidEmailFormat(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Get user details
    public boolean getUserDetails(String email) {
        return users.containsKey(email);
    }
}
