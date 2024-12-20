package com.example.fitsync.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileDAO {
    private static final String PROFILE_FILE = "profile_data.txt";
    private Map<String, String> profileData = new HashMap<>();

    // Save profile data to a text file
    public void saveProfileData(Map < String, String > profileData){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE))) {
            for (Map.Entry<String, String> entry : profileData.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load profile data from the text file
    public Map<String, String> loadProfileData () {
        Map<String, String> profileData = new java.util.HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    profileData.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profileData;
    }
}