package com.bad_walden_stadtwerke.mock;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockHttpClient {

	public static boolean mockConnectionError = false;
	public static boolean mockServerSideError = false;
	private final List<Integer> mockServerSideErrorCodes = java.util.Arrays.asList(4, 10);
	private int mockServerSideRequestCounter = 0;

    public static MockHttpClient newMockHttpClient() {
        return new MockHttpClient();
    }

    private static String mockResponseBodySupplier(HttpRequest request) {
        String uri = request.uri().toString();
        String acceptLanguage = request.headers().allValues("Accept-Language").toString();

        if (uri.equals("https://request-handling.int.bad-walden-stadtwerke.com/test")) {
            return "{\"Hello World\"}";
        }

        if (uri.startsWith("https://request-handling.int.bad-walden-stadtwerke.com/tariff-data")) {
            if (uri.contains("/electricity")) {
                if (acceptLanguage != null && acceptLanguage.toLowerCase().contains("de")) {
                    return "[{\"id\": 1, \"name\": \"Grundversorgung\", \"description\": \"Dieser Tarif bietet eine sichere und zuverlässige Grundstromversorgung für Ihren Haushalt, 100% Ökostrom.\", \"price\": 29, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 2, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet zusätzliche Dienstleistungen und Vorteile für Komfort liebende Kunden, 100% Ökostrom.\", \"price\": 32, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 3, \"name\": \"Super Saver Tarif\", \"description\": \"Unser Super Saver Tarif bietet die niedrigsten Preise für budgetbewusste Kunden, 100% Ökostrom.\", \"price\": 22, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"electricity\"}]";
                } else {
                    return "[{\"id\": 1, \"name\": \"Basic Service\", \"description\": \"This tariff offers a secure and reliable basic electricity supply for your household, 100% green electricity.\", \"price\": 29, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 2, \"name\": \"Comfort Tariff\", \"description\": \"Our Comfort Tariff offers additional services and benefits for comfort-loving customers, 100% green electricity.\", \"price\": 32, \"unit\": \"kWh\", \"category\": \"electricity\"}, {\"id\": 3, \"name\": \"Super Saver Tarif\", \"description\": \"Our Super Saver Tariff offers the lowest prices for budget-conscious customers, 100% green electricity.\", \"price\": 22, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"electricity\"}]";
                }
            }

            if (uri.contains("/gas")) {
                if (acceptLanguage != null && acceptLanguage.toLowerCase().contains("de")) {
                    return "[{\"id\": 4, \"name\": \"Grundversorgung\", \"description\": \"Dieser Tarif bietet Ihnen eine zuverlässige Gasversorgung.\", \"price\": 20, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 5, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet Ihnen weitere Dienstleistungen und Vorteile.\", \"price\": 25, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 6, \"name\": \"Super Sparsamer Tarif\", \"description\": \"Unser Super Saver Tarif bietet Ihnen die niedrigsten Preise und ist absolut budgetfreundlich.\", \"price\": 15, \"unit\": \"m3\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"gas\"}]";
                } else {
                    return "[{\"id\": 4, \"name\": \"Basic Service\", \"description\": \"This tariff offers you a reliable gas supply.\", \"price\": 20, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 5, \"name\": \"Comfort Tariff\", \"description\": \"Our Comfort Tariff offers you additional services and benefits.\", \"price\": 25, \"unit\": \"m3\", \"category\": \"gas\"}, {\"id\": 6, \"name\": \"Super Saver Tarif\", \"description\": \"Our Super Saver Tariff offers you the lowest prices and is absolutely budget-friendly.\", \"price\": 15, \"unit\": \"m3\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"gas\"}]";
                }
            }

            if (uri.contains("/heatpump")) {
                if (acceptLanguage != null && acceptLanguage.toLowerCase().contains("de")) {
                    return "[{\"id\": 7, \"name\": \"Grundversorgung\", \"description\": \"Dieser Tarif bietet eine effiziente Wärmepumpenversorgung, 100% umweltfreundlich.\", \"price\": 10, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 8, \"name\": \"Komfort Tarif\", \"description\": \"Unser Komfort Tarif bietet Ihnen zusätzliche Dienstleistungen und Vorteile, 100% umweltfreundlich.\", \"price\": 15, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 9, \"name\": \"Super Sparsamer Tarif\", \"description\": \"Unser Super Saver Tarif bietet Ihnen die niedrigsten Preise und ist freundlich für Ihr Budget, 100% umweltfreundlich.\", \"price\": 5, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"heat pump\"}]";
                } else {
                    return "[{\"id\": 7, \"name\": \"Basic Service\", \"description\": \"This tariff offers you an efficient heat pump supply, 100% environmentally friendly.\", \"price\": 10, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 8, \"name\": \"Comfort Tariff\", \"description\": \"Our Comfort Tariff offers you additional services and benefits, 100% environmentally friendly.\", \"price\": 15, \"unit\": \"kWh\", \"category\": \"heat pump\"}, {\"id\": 9, \"name\": \"Super Saver Tarif\", \"description\": \"Our Super Saver Tariff offers you the lowest prices and is friendly to your budget, 100% environmentally friendly.\", \"price\": 5, \"unit\": \"kWh\", \"cancellationPeriod\": 3, \"minDuration\": 12, \"category\": \"heat pump\"}]";
                }
            }

            if (uri.contains("/water")) {
                if (acceptLanguage != null && acceptLanguage.toLowerCase().contains("de")) {
                    return "[{\"id\": 0, \"name\": \"Grundversorgung\", \"description\": \"Wir bieten eine sichere und zuverlässige Wasserversorgung für Ihren Haushalt.\", \"price\": 2, \"unit\": \"m3\", \"category\": \"water\"}]";
                } else {
                    return "[{\"id\": 0, \"name\": \"Basic Service\", \"description\": \"We provide a secure and reliable water supply for your household.\", \"price\": 2, \"unit\": \"m3\", \"category\": \"water\"}]";
                }
            } else {
                return "{\"status\": \"Category not recognized\"}";
            }

		}

        if (uri.startsWith("https://request-handling.int.bad-walden-stadtwerke.com/user-data/")) {
            return "{\"status\": \"success\"}";
        }

		return "{\"status\": \"connected\"}";
	}

    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        HttpResponse<T> mockResponse = mock(HttpResponse.class);

        mockServerSideRequestCounter++;
        mockServerSideError = mockServerSideErrorCodes.contains(mockServerSideRequestCounter);


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
}
