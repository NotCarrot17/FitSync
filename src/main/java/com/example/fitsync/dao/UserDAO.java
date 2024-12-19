package com.example.fitsync.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class UserDAO {
    private static final String USER_FILE = "users.dat";
    private static final String KEY_FILE = "key.dat";
    private List<User> users;
    private SecretKey secretKey;

    // User class to store user information
    private static class User implements Serializable {
        private String email;
        private byte[] encryptedPassword; // Store password as encrypted bytes

        public User(String email, byte[] encryptedPassword) {
            this.email = email;
            this.encryptedPassword = encryptedPassword;
        }
    }

    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    // Constructor
    public UserDAO() {
        initializeSecretKey();
        users = loadUsers();
    }

    // Initialize or load the encryption key
    private void initializeSecretKey() {
        try {
            File keyFile = new File(KEY_FILE);
            if (keyFile.exists()) {
                // Load existing key
                try (FileInputStream fis = new FileInputStream(keyFile)) {
                    byte[] keyBytes = new byte[(int) keyFile.length()];
                    fis.read(keyBytes);
                    secretKey = new SecretKeySpec(keyBytes, "AES");
                }
            } else {
                // Generate new key
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(256); // Use 256-bit key
                secretKey = keyGen.generateKey();

                // Save the key
                try (FileOutputStream fos = new FileOutputStream(KEY_FILE)) {
                    fos.write(secretKey.getEncoded());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encrypt password
    private byte[] encryptPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(password.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Decrypt password
    private String decryptPassword(byte[] encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedPassword);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save users to binary file
    private void saveUsers() {
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(USER_FILE))) {

            // Write number of users
            dos.writeInt(users.size());

            // Write each user's data
            for (User user : users) {
                dos.writeUTF(user.email);
                dos.writeInt(user.encryptedPassword.length);
                dos.write(user.encryptedPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users from binary file
    private List<User> loadUsers() {
        List<User> loadedUsers = new ArrayList<>();
        File file = new File(USER_FILE);

        if (!file.exists()) {
            return loadedUsers;
        }

        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(file))) {

            // Read number of users
            int userCount = dis.readInt();

            // Read each user's data
            for (int i = 0; i < userCount; i++) {
                String email = dis.readUTF();
                int passwordLength = dis.readInt();
                byte[] encryptedPassword = new byte[passwordLength];
                dis.readFully(encryptedPassword);

                loadedUsers.add(new User(email, encryptedPassword));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedUsers;
    }

    // Register new user
    public boolean registerUser(String email, String password) {
        if (!isValidEmailFormat(email) || emailExists(email)) {
            return false;
        }

        byte[] encryptedPassword = encryptPassword(password);
        if (encryptedPassword == null) {
            return false;
        }

        users.add(new User(email, encryptedPassword));
        saveUsers();
        return true;
    }

    // Authenticate user
    public boolean authenticate(String email, String password) throws AuthenticationException {
        for (User user : users) {
            if (user.email.equals(email)) {
                String decryptedPassword = decryptPassword(user.encryptedPassword);
                if (decryptedPassword != null && decryptedPassword.equals(password)) {
                    return true;
                } else {
                    throw new AuthenticationException("Invalid password.");
                }
            }
        }
        throw new AuthenticationException("Email does not exist.");
    }

    // Check if email exists
    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.email.equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Email validation
    private boolean isValidEmailFormat(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}