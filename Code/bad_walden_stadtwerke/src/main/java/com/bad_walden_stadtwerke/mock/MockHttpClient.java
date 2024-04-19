package com.bad_walden_stadtwerke.mock;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

public class MockHttpClient {

    private static final boolean mockConnectionError = false;
    private static final boolean mockServerSideError = false;

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

    private static String mockResponseBodySupplier(HttpRequest request){
        //TODO: Implement custom logic
        return "[{\"id\": 1, \"name\": \"Grundversorgung Tarif\", \"description\": \"Dieser Tarif bietet eine sichere und zuverlässige Grundstromversorgung für Ihren Haushalt, 100% Ökostrom.\", \"price\": 29, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 2, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet zusätzliche Dienstleistungen und Vorteile für Komfort liebende Kunden, 100% Ökostrom.\", \"price\": 32, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 3, \"name\": \"Super Saver Tarif\", \"description\": \"Unser Super Saver Tarif bietet die niedrigsten Preise für budgetbewusste Kunden, 100% Ökostrom.\", \"price\": 22, \"unit\": \"kWh\", \"category\": \"electricity\"}]\n";
    }


}
