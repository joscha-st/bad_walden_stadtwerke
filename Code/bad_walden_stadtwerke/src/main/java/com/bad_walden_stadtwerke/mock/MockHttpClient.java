package com.bad_walden_stadtwerke.mock;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.Objects;

public class MockHttpClient {

    public static boolean mockConnectionError = false;
    public static boolean mockServerSideError = false;
    private int mockServerSideErrorCounter = -5;

    public static MockHttpClient newMockHttpClient() {
        return new MockHttpClient();
    }

    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        HttpResponse<T> mockResponse = mock(HttpResponse.class);

        mockServerSideErrorCounter++;
        mockServerSideError = mockServerSideErrorCounter == 0;

        if (mockConnectionError) {
            throw new IOException(new ConnectException());
        } else if (mockServerSideError) {
            when(mockResponse.statusCode()).thenReturn(503);
        } else {
            when(mockResponse.statusCode()).thenReturn(200);
            T mockResponseBody = (T) mockResponseBodySupplier(request);
            when(mockResponse.body()).thenReturn(mockResponseBody);
        }

        return mockResponse;
    }

    private static String mockResponseBodySupplier(HttpRequest request) {
        String uri = request.uri().toString();

        if (uri.equals("https://request-handling.int.bad-walden-stadtwerke.com/test")) {
            return "{\"Hello World\"}";
        }

        if (uri.startsWith("https://request-handling.int.bad-walden-stadtwerke.com/tariff-data")) {
            if (uri.contains("/electricity")) {
                return "[{\"id\": 1, \"name\": \"Grundversorgung Tarif\", \"description\": \"Dieser Tarif bietet eine sichere und zuverlässige Grundstromversorgung für Ihren Haushalt, 100% Ökostrom.\", \"price\": 29, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 2, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet zusätzliche Dienstleistungen und Vorteile für Komfort liebende Kunden, 100% Ökostrom.\", \"price\": 32, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 3, \"name\": \"Super Saver Tarif\", \"description\": \"Unser Super Saver Tarif bietet die niedrigsten Preise für budgetbewusste Kunden, 100% Ökostrom.\", \"price\": 22, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"electricity\"}]";
            }
            else if (uri.contains("/gas")) {
                return "[{\"id\": 4, \"name\": \"Grundversorgung Tarif\", \"description\": \"Dieser Tarif bietet Ihnen eine zuverlässige Gasversorgung, 100% umweltfreundlich.\", \"price\": 20, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 5, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet Ihnen weitere Dienstleistungen und Vorteile, 100% umweltfreundlich.\", \"price\": 25, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 6, \"name\": \"Super Sparsamer Tarif\", \"description\": \"Unser Super Saver Tarif bietet Ihnen die niedrigsten Preise und ist absolut budgetfreundlich, 100% umweltfreundlich.\", \"price\": 15, \"unit\": \"m3\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"gas\"}]";
            }
            else if (uri.contains("/heatpump")) {
                return "[{\"id\": 7, \"name\": \"Grundversorgung Tarif\", \"description\": \"Dieser Tarif bietet eine effiziente Wärmepumpenversorgung, 100% umweltfreundlich.\", \"price\": 10, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 8, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet Ihnen zusätzliche Dienstleistungen und Vorteile, 100% umweltfreundlich.\", \"price\": 15, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 9, \"name\": \"Super Sparsamer Tarif\", \"description\": \"Unser Super Saver Tarif bietet Ihnen die niedrigsten Preise und ist freundlich für Ihr Budget, 100% umweltfreundlich.\", \"price\": 5, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"heat pump\"}]";
            }
            else {
                return "{\"status\": \"Category not recognized\"}";
            }

        }
        if (uri.equals("https://request-handling.int.bad-walden-stadtwerke.com/user-data/billing-address")) {
            return "{\"status\": \"success\"}";
        }

        if (uri.equals("https://request-handling.int.bad-walden-stadtwerke.com/user-data/tariff-selection")) {
            return "{\"status\": \"success\"}";
        }

        return "{\"status\": \"connected\"}";
    }
}
