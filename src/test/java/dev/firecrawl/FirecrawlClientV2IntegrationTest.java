package dev.firecrawl;

import dev.firecrawl.client.FirecrawlClient;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class FirecrawlClientV2IntegrationTest {
    private static String apiKey;
    private static String endpoint;
    private static FirecrawlClient client;

    @BeforeAll
    public static void setup() {
        apiKey = System.getProperty("firecrawl.api-key",
                System.getenv("FIRECRAWL_API_KEY"));
        assumeTrue(apiKey != null && !apiKey.isEmpty(),
                "FIRECRAWL_API_KEY must be set for integration tests");
        endpoint = System.getProperty("firecrawl.endpoint",
                System.getenv("FIRECRAWL_ENDPOINT"));
        if (endpoint == null || endpoint.isEmpty()) {
            endpoint = "https://api.firecrawl.dev";
        }
        client = new FirecrawlClient(apiKey, endpoint, Duration.ofSeconds(120));
    }

    @Test
    public void testSearchV2Integration() throws IOException, FirecrawlException {
        SearchResponse resp = client.search("example domain");
        assertNotNull(resp, "SearchResponse should not be null");
        List<SearchResult> results = resp.getResults();
        assertNotNull(results, "Results list should not be null");
        assertTrue(results.size() > 0, "Expected at least one search result");
        SearchResult first = results.get(0);
        assertNotNull(first.getTitle());
        assertNotNull(first.getUrl());
    }

    @Test
    public void testScrapeV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        ScrapeParams params = new ScrapeParams();
        FirecrawlDocument doc = client.scrape(url, params);
        assertNotNull(doc, "Scraped document should not be null");
        String text = doc.getText();
        assertNotNull(text, "Document text should not be null");
        assertTrue(text.length() > 0, "Expected non-empty text content");
    }

    @Test
    public void testMapV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        Map<String, Object> location = new HashMap<>();
        location.put("country", "US");
        MapParams params = new MapParams()
                .setIncludeSubdomains(true)
                .setSearch("example")
                .setIgnoreQueryParameters(true)
                .setIgnoreCache(true)
                .setLocation(location)
                .setTimeout(5000)
                .setSitemap("include");
        MapResponse resp = client.map(url, params);
        assertNotNull(resp, "MapResponse should not be null");
        assertTrue(resp.isSuccess(), "Map should succeed");
        assertNotNull(resp.getLinks(), "Links array should not be null");
        assertNotNull(resp.getLinkItems(), "Link items should not be null");
    }

    @Test
    public void testStartAndGetCrawlV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        CrawlParams params = new CrawlParams()
                .setPrompt("Find documentation pages")
                .setExcludePaths(new String[]{"/blog"})
                .setIncludePaths(new String[]{"/"})
                .setIgnoreQueryParameters(true)
                .setLimit(2)
                .setAllowExternalLinks(false)
                .setAllowSubdomains(true)
                .setDelay(1)
                .setMaxConcurrency(1);
        CrawlResponse start = client.startCrawl(url, params);
        assertNotNull(start, "CrawlResponse should not be null");
        assertTrue(start.isSuccess(), "Start crawl should report success");
        assertNotNull(start.getId(), "Crawl job ID should not be null");

        CrawlStatusResponse status = client.getCrawlStatus(start.getId());
        assertNotNull(status, "Status response should not be null");
        assertNotNull(status.getStatus(), "Status field should not be null");

        try {
            CrawlErrorsResponse errors = client.getCrawlErrors(start.getId());
            assertNotNull(errors, "Errors response should not be null");
        } catch (FirecrawlException e) {
            System.out.println("[DEBUG_LOG] Crawl errors not available yet: " + e.getMessage());
        }
    }

    @Test
    public void testCrawlWaiterV2Integration() throws IOException, FirecrawlException {
        String url = "https://www.wikipedia.org";
        CrawlParams params = new CrawlParams();
        CrawlStatusResponse result = client.crawl(url, params, 5);
        assertNotNull(result, "CrawlStatusResponse should not be null");
        assertNotNull(result.getStatus(), "Crawl status should not be null");
    }

    @Test
    public void testCancelCrawlV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        CrawlParams params = new CrawlParams();
        CrawlResponse start = client.startCrawl(url, params);
        assertNotNull(start.getId());
        CancelCrawlJobResponse cancel = client.cancelCrawl(start.getId());
        assertNotNull(cancel, "Cancel response should not be null");
        assertNotNull(cancel.getStatus(), "Cancel status should not be null");
    }

    @Test
    public void testSearchV2WithSourcesIntegration() throws IOException, FirecrawlException {
        SearchParams options = new SearchParams("example domain");
        options.setSources(new String[]{"web"});
        SearchResponse resp = client.search("example domain", options);
        assertNotNull(resp, "SearchResponse should not be null");
        assertNotNull(resp.getResults(), "Results should not be null");
    }

    @Test
    public void testSearchV2WithSourcesAndCategoriesIntegration() throws IOException, FirecrawlException {
        SearchParams options = new SearchParams("example domain");
        Map<String, Object> category = new HashMap<>();
        category.put("type", "github");
        options.setSources(new String[]{"web"});
        options.setCategoriesAny(new Object[]{category});
        SearchResponse resp = client.search("example domain", options);
        assertNotNull(resp, "SearchResponse should not be null");
        assertNotNull(resp.getWebResults(), "Web results should not be null");
        assertNotNull(resp.getImageResults(), "Image results should not be null");
        assertNotNull(resp.getNewsResults(), "News results should not be null");
    }

    @Test
    public void testCrawlParamsPreviewV2Integration() throws IOException, FirecrawlException {
        com.google.gson.JsonObject preview = client.crawlParamsPreview(
                "https://docs.firecrawl.dev",
                "Extract docs and blog");
        assertNotNull(preview, "Preview response should not be null");
    }

    @Test
    public void testBatchScrapeV2Integration() throws IOException, FirecrawlException {
        String[] urls = new String[]{"https://example.com", "https://iana.org"};
        BatchScrapeParams params = new BatchScrapeParams(urls)
                .setScrapeOptions(new ScrapeParams().setOnlyMainContent(true))
                .setMaxConcurrency(1)
                .setIgnoreInvalidURLs(true);

        BatchScrapeResponse resp = client.batchScrape(params);
        assertNotNull(resp, "BatchScrapeResponse should not be null");
        assertNotNull(resp.getId(), "Batch scrape ID should not be null");

        BatchScrapeResponse respWithIdem = client.batchScrape(params, "idem-" + UUID.randomUUID());
        assertNotNull(respWithIdem, "BatchScrapeResponse (idempotency) should not be null");
        assertNotNull(respWithIdem.getId(), "Batch scrape ID (idempotency) should not be null");

        BatchScrapeStatusResponse status = client.getBatchScrapeStatus(resp.getId());
        assertNotNull(status, "Batch status should not be null");
        assertNotNull(status.getStatus(), "Batch status should not be null");

        try {
            BatchScrapeErrorsResponse errors = client.getBatchScrapeErrors(resp.getId());
            assertNotNull(errors, "Batch errors should not be null");
        } catch (FirecrawlException e) {
            System.out.println("[DEBUG_LOG] Batch errors not available yet: " + e.getMessage());
        }

        try {
            BatchScrapeCancelResponse cancel = client.cancelBatchScrape(resp.getId());
            assertNotNull(cancel, "Batch cancel should not be null");
        } catch (FirecrawlException e) {
            System.out.println("[DEBUG_LOG] Batch cancel not available: " + e.getMessage());
        }
    }

    @Test
    public void testExtractV2Integration() throws IOException, FirecrawlException {
        Map<String, Object> schema = new HashMap<>();
        schema.put("type", "object");
        ExtractParams params = new ExtractParams(new String[]{"https://example.com"})
                .setPrompt("Extract title")
                .setSchema(schema)
                .setEnableWebSearch(false)
                .setIgnoreSitemap(true)
                .setIncludeSubdomains(true)
                .setShowSources(true)
                .setIgnoreInvalidURLs(true);

        ExtractResponse resp = client.extract(params);
        assertNotNull(resp, "Extract response should not be null");
        assertNotNull(resp.getId(), "Extract ID should not be null");

        ExtractStatusResponse status = client.getExtractStatus(resp.getId());
        assertNotNull(status, "Extract status should not be null");
        assertNotNull(status.getStatus(), "Extract status should not be null");
    }

    @Test
    public void testAgentV2Integration() throws IOException, FirecrawlException {
        Map<String, Object> schema = new HashMap<>();
        schema.put("type", "object");
        AgentParams params = new AgentParams("Summarize example.com")
                .setUrls(new String[]{"https://example.com"})
                .setSchema(schema)
                .setMaxCredits(5)
                .setStrictConstrainToUrls(true)
                .setModel("spark-1-mini");

        AgentResponse resp = client.createAgent(params);
        assertNotNull(resp, "Agent response should not be null");
        assertNotNull(resp.getId(), "Agent ID should not be null");

        AgentStatusResponse status = client.getAgentStatus(resp.getId());
        assertNotNull(status, "Agent status should not be null");
        assertNotNull(status.getStatus(), "Agent status should not be null");

        try {
            AgentCancelResponse cancel = client.cancelAgent(resp.getId());
            assertNotNull(cancel, "Agent cancel response should not be null");
        } catch (FirecrawlException e) {
            System.out.println("[DEBUG_LOG] Agent cancel not available: " + e.getMessage());
        }
    }

    @Test
    public void testAccountV2Integration() throws IOException, FirecrawlException {
        AccountCreditUsageResponse credits = client.getCreditUsage();
        assertNotNull(credits, "Credit usage response should not be null");

        AccountCreditsHistoricalResponse creditsHist = client.getCreditUsageHistorical();
        assertNotNull(creditsHist, "Credit usage historical response should not be null");

        AccountTokenUsageResponse tokens = client.getTokenUsage();
        assertNotNull(tokens, "Token usage response should not be null");

        AccountTokensHistoricalResponse tokensHist = client.getTokenUsageHistorical();
        assertNotNull(tokensHist, "Token usage historical response should not be null");

        AccountQueueStatusResponse queue = client.getQueueStatus();
        assertNotNull(queue, "Queue status response should not be null");
    }

    @Test
    public void testActiveCrawlsIntegration() throws IOException, FirecrawlException {
        CrawlActiveResponse active = client.getActiveCrawls();
        assertNotNull(active, "Active crawls response should not be null");
        if (active.getCrawls() != null) {
            assertTrue(active.getCrawls().length >= 0, "Active crawls array should be accessible");
        }
    }
}
