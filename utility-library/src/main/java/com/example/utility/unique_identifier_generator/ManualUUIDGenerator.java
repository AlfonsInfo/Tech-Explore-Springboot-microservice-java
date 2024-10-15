package com.example.utility.unique_identifier_generator;

import java.security.SecureRandom;

public class ManualUUIDGenerator {
    // Create code manually generate UUID

    public static String generateUUID() {
        SecureRandom random = new SecureRandom();
        byte[] uuidBytes = new byte[16];
        random.nextBytes(uuidBytes);

        // Set version to 4 (UUID version 4)
        uuidBytes[6] &= 0x0f; // Clear the top 4 bits
        uuidBytes[6] |= 0x40; // Set the version to 0100

        // Set variant to 2 (RFC 4122)
        uuidBytes[8] &= 0x3f; // Clear the top 2 bits
        uuidBytes[8] |= 0x80; // Set the variant to 10

        // Convert byte array to hex string
        StringBuilder uuidString = new StringBuilder(36);
        for (int i = 0; i < uuidBytes.length; i++) {
            if (i == 4 || i == 6 || i == 8 || i == 10) {
                uuidString.append('-'); // Add hyphen at specific positions
            }
            uuidString.append(String.format("%02x", uuidBytes[i])); // Format byte to hex
        }

        return uuidString.toString();
    }
}
