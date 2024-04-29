package com.bad_walden_stadtwerke.model.communication;


import com.bad_walden_stadtwerke.model.types.billingAddress.BillingAddress;
import com.bad_walden_stadtwerke.model.types.Tariff;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test {@link StandardOutboundRequestHandler}
 */
public class StandardOutboundRequestHandlerTest {

	@Test
	void whenPostOutboundRequest_thenExpectedResponseIsReturned() {
		String expectedResponse = "{\"Hello World\"}";
		String actualResponse = StandardOutboundRequestHandler.makeStandardPostOutboundRequest("test", "https://request-handling.int.bad-walden-stadtwerke.com/test");
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	void whenGetOutboundRequest_thenExpectedResponseIsReturned() {
		String expectedResponse = "{\"Hello World\"}";
		String actualResponse = StandardOutboundRequestHandler.makeStandardGetOutboundRequest("https://request-handling.int.bad-walden-stadtwerke.com/test");
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	void whenTariffOutboundRequest_thenListOfTariffsIsReturned() {
		List<Tariff> actualResponse = StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
		assertInstanceOf(List.class, actualResponse);
		assertTrue(actualResponse.stream().allMatch(Objects::nonNull));
	}

	@Test
	void whenUpdateBillingAddressForUserOutboundRequest_thenTrueIsReturned() {
		boolean actualResponse = StandardOutboundRequestHandler.makeUpdateBillingAddressForUserOutboundRequest(new BillingAddress("John", "Doe", "Main St", "123", "12345", "Springfield"));
		boolean expectedResponse = true;
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	void whenTariffSelectionForUserOutboundRequest_thenTrueIsReturned() {
		boolean actualResponse = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(new Tariff(1, "Basic", "Basic", 25, "kWh", 12, 12, "electricity"));
		boolean expectedResponse = true;
		assertEquals(expectedResponse, actualResponse);
	}
}