package com.bad_walden_stadtwerke.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bad_walden_stadtwerke.model.types.Tariff;

class JsonParserUtilityTest {

    @Test
    void testParseJson() {
        String json = "[" + "{" + "\"id\": 1," + "\"name\": \"Tariff1\"," + "\"description\": \"Description1\"," + "\"price\": 100," + "\"unit\": \"Unit1\"," + "\"minDuration\": 6," +
                "\"cancellationPeriod\": 1," + "\"category\": \"Category1\"" + "}," + "{" + "\"id\": 2," + "\"name\": \"Tariff2\"," + "\"description\": \"Description2\"," + "\"price\": 200," +
                "\"unit\": \"Unit2\"," + "\"minDuration\": 12," + "\"cancellationPeriod\": 2," + "\"category\": \"Category2\"" + "}" + "]";

        List<Tariff> parsedTariffs = JsonParserUtility.parseJson(json, Tariff::new);

        assertEquals(2, parsedTariffs.size());

        Tariff tariff1 = parsedTariffs.get(0);
        assertEquals(1, tariff1.getId());
        assertEquals("Tariff1", tariff1.getName());
        assertEquals("Description1", tariff1.getDescription());
        assertEquals(100, tariff1.getPrice());
        assertEquals("Unit1", tariff1.getUnit());
        assertEquals(6, tariff1.getMinDuration());
        assertEquals(1, tariff1.getCancellationPeriod());
        assertEquals("Category1", tariff1.getCategory());

        Tariff tariff2 = parsedTariffs.get(1);
        assertEquals(2, tariff2.getId());
        assertEquals("Tariff2", tariff2.getName());
        assertEquals("Description2", tariff2.getDescription());
        assertEquals(200, tariff2.getPrice());
        assertEquals("Unit2", tariff2.getUnit());
        assertEquals(12, tariff2.getMinDuration());
        assertEquals(2, tariff2.getCancellationPeriod());
        assertEquals("Category2", tariff2.getCategory());
    }
}