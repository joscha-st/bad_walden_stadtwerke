package com.bad_walden_stadtwerke.mock;

public class MockActiveSession {
	private static SessionUser user = new SessionUser();
	private static String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

	public static SessionUser getActiveUser() {
		return user;
	}

	public static String getBearerToken() {
		return bearerToken;
	}

	public static void refreshCurrentSessionDataFromWebServer() {
	}


	public static class SessionUser {
		private int userId = 57782;
		private String friendlyDisplayName = "Paul Peter";

		private boolean hasDoneInitialSignUp = false;

		public String getFriendlyDisplayName() {
			return friendlyDisplayName;
		}

		public boolean getHasDoneInitialSignUp() {
			return hasDoneInitialSignUp;
		}
	}
}
