package com.bad_walden_stadtwerke.components.mock;

import static org.mockito.Mockito.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;


public class MockHttpClient {

    public static MockHttpClient newMockHttpClient() {
        return new MockHttpClient();
    }

    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) {
        // Mocking a HttpResponse
        HttpResponse<T> mockResponse = mock(HttpResponse.class);

        when(mockResponse.statusCode()).thenReturn(200);

        T mockResponseBody = (T) mockResponseBodySupplier(request);
        when(mockResponse.body()).thenReturn(mockResponseBody);

        return mockResponse;
    }

    private static String mockResponseBodySupplier(HttpRequest request){
        //TODO: Implement custom logic
        return "{\"message\": \"Mock response message\"}";
    }


}
