package dev.firecrawl;

import com.google.gson.Gson;
import dev.firecrawl.model.CrawlStatusResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CrawlStatusResponseV2ModelTest {
    private static final Gson GSON = new Gson();

    @Test
    public void parsesAdditionalFields() {
        String json = "{\n" +
                "  \"success\": true,\n" +
                "  \"status\": \"running\",\n" +
                "  \"total\": 10,\n" +
                "  \"completed\": 3,\n" +
                "  \"creditsUsed\": 5,\n" +
                "  \"expiresAt\": \"2025-01-01T00:00:00Z\",\n" +
                "  \"next\": \"cursor\",\n" +
                "  \"data\": [ { \"markdown\": \"doc\" } ]\n" +
                "}";
        CrawlStatusResponse resp = GSON.fromJson(json, CrawlStatusResponse.class);
        assertEquals("running", resp.getStatus());
        assertEquals(10, resp.getTotal());
        assertEquals(3, resp.getCompleted());
        assertEquals(5, resp.getCreditsUsed());
        assertEquals("2025-01-01T00:00:00Z", resp.getExpiresAt());
        assertEquals("cursor", resp.getNext());
        assertNotNull(resp.getData());
        assertEquals(1, resp.getData().length);
    }
}
