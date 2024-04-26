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

    public static MockHttpClient newMockHttpClient() {
        return new MockHttpClient();
    }

    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        HttpResponse<T> mockResponse = mock(HttpResponse.class);

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
                return "[{\"id\": 1, \"name\": \"Grundversorgung Tarif\", \"description\": \"Dieser Tarif bietet eine sichere und zuverlässige Grundstromversorgung für Ihren Haushalt, 100% Ökostrom.\", \"price\": 29, \"unit\": \"kWh\", \"category\": \"electricity\"}]";
            }
            else if (uri.contains("/gas")) {
                return "[{\"id\": 2, \"name\": \"Gas Tarif\", \"description\": \"Dieser Tarif bietet eine sichere und zuverlässige Gasversorgung für Ihren Haushalt, 100% umweltfreundlich.\", \"price\": 19, \"unit\": \"m3\", \"category\": \"gas\"}]";
            }
            else if (uri.contains("/heatpump")) {
                return "[{\"id\": 3, \"name\": \"Heat Pump Tarif\", \"description\": \"Dieser Tarif bietet niedrigste Preise für umweltfreundliche Wärmeversorgung.\", \"price\": 12, \"unit\": \"kWh\", \"category\": \"heat pump\"}]";
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
