package com.bad_walden_stadtwerke.communication;

import static com.bad_walden_stadtwerke.ui.components.mainApplication.sidebar.SidebarItems.messages;

import java.io.IOException;
import java.net.URI;

import com.bad_walden_stadtwerke.logic.BillingAddress;
import com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.mock.MockHttpClient;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class StandardOutboundRequestHandler {

    static MockHttpClient client;
    static final String standardEndpointUrl = "https://request-handling.int.bad-walden-stadtwerke.com/";

    public static String makeStandardOutboundRequest(String jsonPayload, String endpointUrl) {
        CreateNewClientIfNoneExists();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpointUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + MockActiveSession.getBearerToken())
                .header("Accept-Language", getActiveLanguageAndCountryForHeader())
                .POST(BodyPublishers.ofString(jsonPayload))
                .build();
        System.out.println("Communication: StandardOutboundRequest prepared: " + request.toString());

        HttpResponse<String> response = sendRequestToServer(request);

        return response != null ? response.body() : null;
    }

    public static List<Tariff> makeTariffOutboundRequest(String category) {
        return BadWJsonParser.parseJson(makeStandardOutboundRequest("category: " + category, "https://request-handling.int.bad-walden-stadtwerke.com/tariff-data/"), Tariff::new);
    }

    public static boolean makeUpdateBillingAddressForUserOutboundRequest(BillingAddress billingAddress) {
        String response = makeStandardOutboundRequest(billingAddress.toJson(), "https://request-handling.int.bad-walden-stadtwerke.com/user-data/billing-address");
        return Objects.equals(response, "{\"status\": \"success\"}");
    }

    private static HttpResponse<String> sendRequestToServer(HttpRequest request) {
        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            System.out.println("Communication: response code: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            displayNetworkError(e.getCause().toString());
            return null;
        }

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response;
        } else {
            displayNetworkError(getStatusCodeErrorDescription(response.statusCode()));
            return null;
        }
    }

    private static void CreateNewClientIfNoneExists() {
        if (client == null) {
        client = MockHttpClient.newMockHttpClient();
        System.out.println("Communication: New client initialized");
        }
    }

    private static String getActiveLanguageAndCountryForHeader() {
        Locale activeLanguage = LanguageController.getLanguage();
        return activeLanguage.getLanguage() + "-" + activeLanguage.getCountry();
    }

    private static void displayNetworkError(String error) {
        System.out.println("Communication: Error: " + error);
        ExceptionPopup.showErrorPopup("Network Error", error);
    }

    private static String getStatusCodeErrorDescription(int code) {
        switch(code){
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
}
