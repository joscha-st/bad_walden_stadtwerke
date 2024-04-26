package com.bad_walden_stadtwerke.mock;

public class MockActiveSession {
    private static SessionUser user = new SessionUser();

    public static SessionUser getActiveUser() {
        return user;
    }

    private static String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    public static String getBearerToken() {
        return bearerToken;
    }

    public static class SessionUser {
        private int userId = 57782;
        private String friendlyDisplayName = "Paul Peter";

        public int getUserId() {
            return userId;
        }

        public String getFriendlyDisplayName() {
            return friendlyDisplayName;
        }
    }
}
