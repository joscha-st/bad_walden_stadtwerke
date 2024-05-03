/**
 * The StandardOutboundRequestHandler class provides methods for making standard outbound HTTP requests to the server.
 * <p>
 * It includes methods for sending POST and GET requests, updating user data, retrieving tariff information,
 * and handling errors related to network communication.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.communication;

import com.bad_walden_stadtwerke.utility.CentralLoggingUtility;
import com.bad_walden_stadtwerke.utility.JsonParserUtility;
import com.bad_walden_stadtwerke.model.types.billingAddress.BillingAddress;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.mock.MockHttpClient;
import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.language.LanguageController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class StandardOutboundRequestHandler {

	static MockHttpClient client;
	private static ResourceBundle messages;


	/**
     * Sends a standard POST request to the specified endpoint URL with the provided JSON payload.
     *
     * @param jsonPayload  The JSON payload to send.
     * @param endpointUrl  The URL of the endpoint.
     * @return The response body as a string.
     */
	public static String makeStandardPostOutboundRequest(String jsonPayload, String endpointUrl) {
		CreateNewClientIfNoneExists();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpointUrl)).header("Content-Type", "application/json").header("Authorization", "Basic " + MockActiveSession.getBearerToken()).header("Accept-Language", getActiveLanguageAndCountryForHeader()).POST(BodyPublishers.ofString(jsonPayload)).build();
		CentralLoggingUtility.handleEvent("Communication", "StandardPostOutboundRequest prepared: " + request.toString());

		HttpResponse<String> response = sendRequestToServer(request);

		return response != null ? response.body() : null;
	}

	/**
     * Sends a standard GET request to the specified endpoint URL.
     *
     * @param endpointUrl  The URL of the endpoint.
     * @return The response body as a string.
     */
	public static String makeStandardGetOutboundRequest(String endpointUrl) {
		CreateNewClientIfNoneExists();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpointUrl)).header("Content-Type", "application/json").header("Authorization", "Basic " + MockActiveSession.getBearerToken()).header("Accept-Language", getActiveLanguageAndCountryForHeader()).GET().build();
		CentralLoggingUtility.handleEvent("Communication", "StandardGetOutboundRequest prepared: " + request.toString());

		HttpResponse<String> response = sendRequestToServer(request);

		return response != null ? response.body() : null;
	}


	/**
     * Sends a standard POST request to update user data.
     *
     * @param jsonPayload  The JSON payload containing the updated user data.
     * @param endpointUrl  The URL of the endpoint.
     * @return True if the request was successful, false otherwise.
     */
	public static boolean makeStandardUpdateRequest(String jsonPayload, String endpointUrl) {
		String response = makeStandardPostOutboundRequest(jsonPayload, endpointUrl);
		return Objects.equals(response, "{\"status\": \"success\"}");
	}


	/**
     * Retrieves tariff information for a specific category.
     *
     * @param category  The category of tariffs to retrieve.
     * @return A list of tariff objects.
     */
	public static List<Tariff> makeTariffOutboundRequest(String category) {
		String request = makeStandardGetOutboundRequest("https://request-handling.int.bad-walden-stadtwerke.com/tariff-data/" + category);
		if (request != null){
		return JsonParserUtility.parseJson(request, Tariff::new);
		}
		else {
			return null;
		}
	}

/**
     * Updates the billing address for a user.
     *
     * @param billingAddress  The billing address object.
     * @return True if the request was successful, false otherwise.
     */
	public static boolean makeUpdateBillingAddressForUserOutboundRequest(BillingAddress billingAddress) {
		String response = makeStandardPostOutboundRequest(billingAddress.toJson(), "https://request-handling.int.bad-walden-stadtwerke.com/user-data/billing-address");
		return Objects.equals(response, "{\"status\": \"success\"}");
	}

	 /**
     * Selects a tariff for a user.
     *
     * @param tariff  The tariff object to select.
     * @return True if the request was successful, false otherwise.
     */
	public static boolean makeTariffSelectionForUserOutboundRequest(Tariff tariff) {
		String response = makeStandardPostOutboundRequest(tariff.idToJson(), "https://request-handling.int.bad-walden-stadtwerke.com/user-data/tariff-selection");
		return Objects.equals(response, "{\"status\": \"success\"}");
	}

	private static HttpResponse<String> sendRequestToServer(HttpRequest request) {
		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			CentralLoggingUtility.handleEvent("Communication", "response code: " + response.statusCode());
		} catch (IOException | InterruptedException e) {
			CentralLoggingUtility.handleException("Communication", e);
			displayNetworkError(e.getCause().toString());
			return null;
		}

		if (response.statusCode() >= 200 && response.statusCode() < 300) {
			return response;
		} else {
			CentralLoggingUtility.handleEvent("Communication", getStatusCodeErrorDescription(response.statusCode()));
			displayNetworkError(getStatusCodeErrorDescription(response.statusCode()));
			return null;
		}
	}

	private static void CreateNewClientIfNoneExists() {
		if (client == null) {
			client = MockHttpClient.newMockHttpClient();
			CentralLoggingUtility.handleEvent("Communication", "New client initialized");
		}
	}

	private static String getActiveLanguageAndCountryForHeader() {
		Locale activeLanguage = LanguageController.getLanguage();
		return activeLanguage.getLanguage() + "-" + activeLanguage.getCountry();
	}

	private static void displayNetworkError(String error) {
		updateResourceBundleToCurrentLanguage();
		ExceptionPopup.showErrorPopup(messages.getString("webRequestsErrorTitle"), error);
	}

	private static String getStatusCodeErrorDescription(int code) {
		updateResourceBundleToCurrentLanguage();
		switch (code) {
			case 400:
				return messages.getString("webRequestsBadRequest");
			case 401:
				return messages.getString("webRequestsUnauthorizedAccess");
			case 403:
				return messages.getString("webRequestsForbiddenAccess");
			case 404:
				return messages.getString("webRequestsNotFound");
			case 500:
				return messages.getString("webRequestsInternalServerError");
			case 502:
				return messages.getString("webRequestsBadGateway");
			case 503:
				return messages.getString("webRequestsServiceUnavailable");
			default:
				return messages.getString("webRequestsDefaultError");
		}
	}

	private static void updateResourceBundleToCurrentLanguage() {
		messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
	}
}
