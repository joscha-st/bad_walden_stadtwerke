package com.bad_walden_stadtwerke.mock;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

public class MockHttpClient {

    private static final boolean mockConnectionError = false;
    private static final boolean mockServerSideError = true;

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
        return "{\"message\": \"Mock response message\"}";
    }


}
