package com.bad_walden_stadtwerke.components.communication;

import java.io.IOException;
import java.net.URI;
import com.bad_walden_stadtwerke.components.mock.MockHttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class StandardOutboundRequestHandler {
    public static void main(String[] args) throws IOException, InterruptedException {

        // TODO: remove this testcase from main and add custom request possibility
        MockHttpClient client = MockHttpClient.newMockHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.org/"))
                .build();

        HttpResponse<String> response =
                client.send(request, BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

}
