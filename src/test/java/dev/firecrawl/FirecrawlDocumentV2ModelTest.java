package dev.firecrawl;

import com.google.gson.Gson;
import dev.firecrawl.model.FirecrawlDocument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirecrawlDocumentV2ModelTest {
    private static final Gson GSON = new Gson();

    @Test
    public void deserializesActionsAndChangeTracking() {
        String json = "{\n" +
                "  \"markdown\": \"Some text\",\n" +
                "  \"links\": [\"https://a.com\", \"https://b.com\"],\n" +
                "  \"actions\": {\n" +
                "    \"screenshots\": [\"img1\"],\n" +
                "    \"scrapes\": [ { \"url\": \"https://x.com\", \"html\": \"<p>x</p>\" } ],\n" +
                "    \"javascriptReturns\": [ { \"type\": \"number\", \"value\": 42 } ],\n" +
                "    \"pdfs\": [\"pdf1\"]\n" +
                "  },\n" +
                "  \"changeTracking\": {\n" +
                "    \"previousScrapeAt\": \"2024-01-01T00:00:00Z\",\n" +
                "    \"changeStatus\": \"changed\",\n" +
                "    \"visibility\": \"public\",\n" +
                "    \"diff\": \"-a +b\"\n" +
                "  },\n" +
                "  \"branding\": { \"logo\": \"https://logo\" },\n" +
                "  \"warning\": \"partial\",\n" +
                "  \"metadata\": { \"foo\": \"bar\" }\n" +
                "}";
        FirecrawlDocument doc = GSON.fromJson(json, FirecrawlDocument.class);
        assertEquals("Some text", doc.getText());
        assertNotNull(doc.getLinks());
        assertEquals(2, doc.getLinks().length);
        assertNotNull(doc.getActions());
        assertNotNull(doc.getActions().getScreenshots());
        assertEquals(1, doc.getActions().getScreenshots().length);
        assertNotNull(doc.getActions().getScrapes());
        assertEquals("https://x.com", doc.getActions().getScrapes()[0].getUrl());
        assertNotNull(doc.getActions().getJavascriptReturns());
        assertEquals("number", doc.getActions().getJavascriptReturns()[0].getType());
        assertNotNull(doc.getActions().getPdfs());
        assertNotNull(doc.getChangeTracking());
        assertEquals("changed", doc.getChangeTracking().getChangeStatus());
        assertNotNull(doc.getBranding());
        assertEquals("partial", doc.getWarning());
    }

    @Test
    public void equalsAndHashCodeConsistent() {
        String json = "{\n" +
                "  \"markdown\": \"Text\",\n" +
                "  \"links\": [\"https://a.com\"]\n" +
                "}";
        FirecrawlDocument d1 = GSON.fromJson(json, FirecrawlDocument.class);
        FirecrawlDocument d2 = GSON.fromJson(json, FirecrawlDocument.class);
        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertTrue(d1.toString().contains("FirecrawlDocument"));
    }
}
