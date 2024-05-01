/**
 * Provides tools for simulating HTTP client interactions, specifically for development and testing purposes.
 * The MockHttpClient class mimics the behavior of an HTTP client, allowing developers to test the handling of network interactions and responses without real server communication.
 */
package com.bad_walden_stadtwerke.mock;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Simulates HTTP client requests and responses for testing network interactions within the application.
 * This class allows developers to define specific scenarios such as connection errors or server-side errors
 * without the need to connect to an actual web service.
 */
public class MockHttpClient {

    public static boolean mockConnectionError = false;
    public static boolean mockServerSideError = false;
    private int mockServerSideErrorCounter = -2;

    /**
     * Factory method to create a new instance of a MockHttpClient.
     *
     * @return A new instance of MockHttpClient.
     */
    public static MockHttpClient newMockHttpClient() {
        return new MockHttpClient();
    }

    /**
     * Simulates sending an HTTP request and handling the response.
     * This method allows for the configuration of response scenarios such as connection failures or server errors.
     *
     * @param request The HTTP request to send.
     * @param responseBodyHandler A handler to process the body of the HTTP response.
     * @param <T> The type of the response body.
     * @return An HTTP response object as specified by the responseBodyHandler.
     * @throws IOException If there is a simulated connection error.
     * @throws InterruptedException If the thread is interrupted during execution.
     */
    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        HttpResponse<T> mockResponse = mock(HttpResponse.class);

        mockServerSideErrorCounter++;
        mockServerSideError = mockServerSideErrorCounter == 0;

        if (mockConnectionError) {
            throw new IOException(new ConnectException("Simulated connection failure."));
        } else if (mockServerSideError) {
            when(mockResponse.statusCode()).thenReturn(503); // Simulate a server-side error status code.
        } else {
            when(mockResponse.statusCode()).thenReturn(200); // Simulate a success status code.
            T mockResponseBody = (T) mockResponseBodySupplier(request); // Obtain the simulated response body.
            when(mockResponse.body()).thenReturn(mockResponseBody);
        }

        return mockResponse;
    }

    /**
     * Simulates generating response bodies based on the URI of the request.
     * This method provides custom mock responses for different types of requests, mimicking actual server responses.
     *
     * @param request The HTTP request for which to generate a response body.
     * @return
