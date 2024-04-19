package com.bad_walden_stadtwerke.communication;

import java.io.IOException;
import java.net.URI;

import com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.mock.MockHttpClient;
import com.bad_walden_stadtwerke.mock.MockActiveSession;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class StandardOutboundRequestHandler {

    static MockHttpClient client;
    static final String endpointUrl = "https://request-handling.int.bad-walden-stadtwerke.com/";

    public static String makeStandardOutboundRequest(String jsonPayload) {
        CreateNewClientIfNoneExists();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpointUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + MockActiveSession.getBearerToken())
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
        System.out.println("Communication: StandardOutboundRequest prepared: " + request.toString());

        HttpResponse<String> response = sendRequestToServer(request);

        return response.body();
    }

    private static HttpResponse<String> sendRequestToServer(HttpRequest request) {
        HttpResponse<String> response = null;
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

    private static void displayNetworkError(String error) {
        System.out.println("Communication: Error: " + error);
        ExceptionPopup.showErrorPopup("Network Error", error);
    }

    private static String getStatusCodeErrorDescription(int code) {
        switch(code){
            case 400:
                return "400 - Oops, we couldn't understand your request. Please check and try again.";
            case 401:
                return "401 - Sorry, you'll need to log in to access this.";
            case 403:
                return "403 - Sorry, you don't have permission to access this.";
            case 404:
                return "404 - Sorry, we couldn't find what you're looking for. Please have an admin check the configuration";
            case 500:
                return "500 - Oops, something went wrong on our side. We're looking into it.";
            case 502:
                return "502 - There seems to be an issue with the service. Please try again later.";
            case 503:
                return "503 - Sorry, the service is unavailable right now. Please try again later.";
            default:
                return "An unexpected error has occurred. Please try again later.";
        }
    }
}
