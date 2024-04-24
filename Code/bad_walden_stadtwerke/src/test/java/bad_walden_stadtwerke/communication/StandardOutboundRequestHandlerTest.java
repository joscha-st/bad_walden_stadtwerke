package bad_walden_stadtwerke.communication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.sales.types.Tariff;

/**
 * Unit test {@link StandardOutboundRequestHandler}
 */
public class StandardOutboundRequestHandlerTest {

    @Test
    void testMakeStandardOutboundRequest() {
        String expectedResponse = "{\"Hello World\"}";

        String actualResponse = StandardOutboundRequestHandler.makeStandardOutboundRequest("test", "https://request-handling.int.bad-walden-stadtwerke.com/test");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testMakeTariffOutboundRequest() {
        List<Tariff> actualResponse = StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");

        assertInstanceOf(List.class, actualResponse);
        assertTrue(actualResponse.stream().allMatch(Objects::nonNull));
    }

}