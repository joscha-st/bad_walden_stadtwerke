package com.bad_walden_stadtwerke.mock;

public class MockActiveSession {
    private static int userId = 57782;
    private static String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
    public static int getUserId() {
        return userId;
    }
    public static String getBearerToken() {
        return bearerToken;
    }
    private static String friendlyDisplayName = "Paul Peter";
    public static String getFriendlyDisplayName() {
        return friendlyDisplayName;
    }
}
