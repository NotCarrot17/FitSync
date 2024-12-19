package com.example.fitsync.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileDAO {
    private static final String PROFILE_FILE = "profile.dat";
    private Map<String, String> profileData = new HashMap<>();

    public ProfileDAO() {
        loadProfile();
    }

    // Save profile data to a file
    public void saveProfile(String key, String value) {
        profileData.put(key, value);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE))) {
            for (Map.Entry<String, String> entry : profileData.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load profile data from a file
    private void loadProfile() {
        File file = new File(PROFILE_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    profileData.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get profile value by key
    public String getProfile(String key) {
        return profileData.getOrDefault(key, "");
    }
}
