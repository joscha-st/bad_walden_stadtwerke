package bad_walden_stadtwerke.communication;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.sales.types.Tariff;

public class StandardOutboundRequestHandlerTest {

    @Test
    void testMakeStandardOutboundRequest() throws Exception {
        String expectedResponse = "{\"Hello World\"}";

        String actualResponse = StandardOutboundRequestHandler.makeStandardOutboundRequest("test", "https://request-handling.int.bad-walden-stadtwerke.com/test");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testMakeTariffOutboundRequest() throws Exception {
        // List<Tariff> expectedResponse = "{\"Hello World\"}";

        List<Tariff> actualResponse = StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");

        // assertEquals(expectedResponse, actualResponse);
    }

}