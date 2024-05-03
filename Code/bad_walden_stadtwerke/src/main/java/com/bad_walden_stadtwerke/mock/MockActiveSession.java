/**
 * The MockActiveSession class simulates an active session for testing purposes.
 * <p>
 * It provides mock data for the active user, including user ID, friendly display name, and initial sign-up status.
 * The class also includes a bearer token for authentication purposes.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.mock;

public class MockActiveSession {
	private static SessionUser user = new SessionUser();
	private static String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

	/**
     * Retrieves the active user from the mock session.
     *
     * @return The active user.
     */
	public static SessionUser getActiveUser() {
		return user;
	}

	/**
     * Retrieves the bearer token for the active session.
     *
     * @return The bearer token.
     */
	public static String getBearerToken() {
		return bearerToken;
	}

	/**
     * Refreshes the current session data from the web server.
     * This method is currently empty as it is a mock implementation.
     */
	public static void refreshCurrentSessionDataFromWebServer() {
	}

/**
     * The SessionUser class represents the user in the mock active session.
     */
	public static class SessionUser {
		private int userId = 57782;
		private String friendlyDisplayName = "Paul Peter";

		private boolean hasDoneInitialSignUp = false;

		/**
         * Retrieves the friendly display name of the user.
         *
         * @return The friendly display name.
         */
		public String getFriendlyDisplayName() {
			return friendlyDisplayName;
		}

		/**
         * Checks if the user has completed the initial sign-up process.
         *
         * @return True if the user has completed the initial sign-up, otherwise false.
         */
		public boolean getHasDoneInitialSignUp() {
			return hasDoneInitialSignUp;
		}
	}
}
