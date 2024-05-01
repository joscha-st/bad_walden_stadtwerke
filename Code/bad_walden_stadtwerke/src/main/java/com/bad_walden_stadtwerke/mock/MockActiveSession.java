/**
 * Provides mock classes and methods for simulating active user sessions and their attributes within the application.
 * This package is intended for use in development and testing environments where interaction with actual user sessions is impractical.
 */
package com.bad_walden_stadtwerke.mock;

/**
 * Simulates an active session management by providing static access to user session data and authentication tokens.
 * This class is used primarily for testing and development to mimic session-based behaviors without real user data or backend communication.
 */
public class MockActiveSession {
    private static SessionUser user = new SessionUser();
    private static String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    /**
     * Retrieves a mock {@link SessionUser} object representing the current user of the session.
     *
     * @return The {@code SessionUser} instance containing mock user data.
     */
    public static SessionUser getActiveUser() {
        return user;
    }

    /**
     * Retrieves the mock bearer token associated with the current session.
     *
     * @return A string representing the bearer token.
     */
    public static String getBearerToken() {
        return bearerToken;
    }

    /**
     * Simulates the action of refreshing current session data from a web server. This method is a placeholder
     * to mimic real-world session data refresh scenarios in a testing or development environment.
     */
    public static void refreshCurrentSessionDataFromWebServer() {
        // This method would be implemented to simulate refreshing session data from a server.
    }

    /**
     * A nested class representing a user in a session. Contains mock data such as user identifier and display name.
     */
    public static class SessionUser {
        private int userId = 57782;
        private String friendlyDisplayName = "Paul Peter";

        private boolean hasDoneInitialSignUp = false;

        /**
         * Retrieves the friendly display name of the user.
         *
         * @return The user's friendly display name.
         */
        public String getFriendlyDisplayName() {
            return friendlyDisplayName;
        }

        /**
         * Checks if the user has completed the initial sign-up process.
         *
         * @return {@code true} if the user has completed the initial sign-up process, otherwise {@code false}.
         */
        public boolean getHasDoneInitialSignUp() {
            return hasDoneInitialSignUp;
        }
    }
}
