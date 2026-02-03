package dev.firecrawl.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.firecrawl.exception.ApiException;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.*;
import dev.firecrawl.util.HttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

/**
 * Client for the Firecrawl API.
 */
public class FirecrawlClient {
    private final String apiKey;
    private final String apiUrl;
    private final OkHttpClient httpClient;
    private final Gson gson = new Gson();

    // Service instances
    private final SearchService searchService;
    private final ScrapeService scrapeService;
    private final MapService mapService;
    private final CrawlService crawlService;
    private final BatchScrapeService batchScrapeService;
    private final AgentService agentService;
    private final ExtractService extractService;
    private final AccountService accountService;

    /**
     * Creates a new FirecrawlClient with the specified API key, API URL, and timeout.
     *
     * @param apiKey the API key
     * @param apiUrl the API URL (can be null to use the default URL)
     * @param timeout the request timeout
     */
    public FirecrawlClient(String apiKey, String apiUrl, Duration timeout) {
        this.apiKey = apiKey != null && !apiKey.isEmpty()
                ? apiKey
                : System.getenv("FIRECRAWL_API_KEY");
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key must be provided");
        }
        this.apiUrl = (apiUrl != null && !apiUrl.isEmpty())
                ? apiUrl
                : System.getenv().getOrDefault("FIRECRAWL_API_URL", "https://api.firecrawl.dev");
        Duration t = timeout != null ? timeout : Duration.ofSeconds(120);
        this.httpClient = new OkHttpClient.Builder()
                .callTimeout(t)
                .build();

        // Initialize services
        this.searchService = new SearchService(this);
        this.scrapeService = new ScrapeService(this);
        this.mapService = new MapService(this);
        this.crawlService = new CrawlService(this);
        this.batchScrapeService = new BatchScrapeService(this);
        this.agentService = new AgentService(this);
        this.extractService = new ExtractService(this);
        this.accountService = new AccountService(this);
    }

    /**
     * Searches for the specified query with the specified parameters.
     *
     * @param params the search parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public SearchResponse search(SearchParams params) throws IOException, FirecrawlException {
        return searchService.search(params);
    }

    /**
     * v2: Searches for the specified query with optional parameters.
     */
    public SearchResponse search(String query) throws IOException, FirecrawlException {
        return search(new SearchParams(query));
    }

    /**
     * v2: Searches for the specified query with optional parameters.
     */
    public SearchResponse search(String query, SearchParams options) throws IOException, FirecrawlException {
        SearchParams sp = options != null ? options : new SearchParams(query);
        sp.setQuery(query);
        return search(sp);
    }

    /**
     * Searches for the specified query with the specified legacy parameters.
     *
     * @param query the search query
     * @param legacyParams the legacy parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     * @deprecated use {@link #search(String, SearchParams)} instead
     */
    @Deprecated
    public SearchResponse search(String query, JsonObject legacyParams) throws IOException, FirecrawlException {
        return searchService.search(query, legacyParams);
    }

    /**
     * Scrapes the specified URL with the specified parameters.
     *
     * @param url the URL to scrape
     * @param params the scrape parameters
     * @return the scraped document
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public FirecrawlDocument scrapeURL(String url, ScrapeParams params) throws IOException, FirecrawlException {
        return scrapeService.scrapeURL(url, params);
    }

    /**
     * v2: Scrapes the specified URL with the specified parameters.
     * Equivalent to scrapeURL.
     */
    public FirecrawlDocument scrape(String url, ScrapeParams params) throws IOException, FirecrawlException {
        return scrapeService.scrapeURL(url, params);
    }

    /**
     * Maps the specified URL with the specified parameters.
     *
     * @param url the URL to map
     * @param params the map parameters
     * @return the map response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public MapResponse mapURL(String url, MapParams params) throws IOException, FirecrawlException {
        return mapService.mapURL(url, params);
    }

    /**
     * v2: Maps the specified URL with the specified parameters.
     * Equivalent to mapURL.
     */
    public MapResponse map(String url, MapParams params) throws IOException, FirecrawlException {
        return mapService.mapURL(url, params);
    }

    /**
     * Crawls the specified URL with the specified parameters.
     *
     * @param url the URL to crawl
     * @param params the crawl parameters
     * @param idempotencyKey the idempotency key
     * @param pollInterval the polling interval in seconds (default: 2)
     * @return the crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlStatusResponse crawlURL(String url, CrawlParams params, String idempotencyKey, int... pollInterval) throws IOException, FirecrawlException {
        return crawlService.crawlURL(url, params, idempotencyKey, pollInterval);
    }

    /**
     * v2: Crawls the specified URL (waiter). Generates an idempotency key.
     */
    public CrawlStatusResponse crawl(String url, CrawlParams params, int... pollInterval) throws IOException, FirecrawlException {
        String key = java.util.UUID.randomUUID().toString();
        return crawlService.crawlURL(url, params, key, pollInterval);
    }

    /**
     * v2: Starts a crawl job (async) without idempotency key.
     */
    public CrawlResponse startCrawl(String url, CrawlParams params) throws IOException, FirecrawlException {
        return crawlService.asyncCrawlURL(url, params, null);
    }

    /**
     * v2: Starts a crawl job (async) with idempotency key.
     */
    public CrawlResponse startCrawl(String url, CrawlParams params, String idempotencyKey) throws IOException, FirecrawlException {
        return crawlService.asyncCrawlURL(url, params, idempotencyKey);
    }

    /**
     * Asynchronously crawls the specified URL with the specified parameters.
     *
     * @param url the URL to crawl
     * @param params the crawl parameters
     * @param idempotencyKey the idempotency key
     * @return the crawl response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlResponse asyncCrawlURL(String url, CrawlParams params, String idempotencyKey) throws IOException, FirecrawlException {
        return crawlService.asyncCrawlURL(url, params, idempotencyKey);
    }

    /**
     * Checks the status of the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlStatusResponse checkCrawlStatus(String id) throws IOException, FirecrawlException {
        return crawlService.checkCrawlStatus(id);
    }

    /**
     * v2: Gets the status of a crawl job (alias of checkCrawlStatus).
     */
    public CrawlStatusResponse getCrawlStatus(String id) throws IOException, FirecrawlException {
        return crawlService.checkCrawlStatus(id);
    }

    /**
     * Cancels the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the cancel crawl job response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CancelCrawlJobResponse cancelCrawlJob(String id) throws IOException, FirecrawlException {
        return crawlService.cancelCrawlJob(id);
    }

    /**
     * v2: Cancels a crawl job (alias of cancelCrawlJob).
     */
    public CancelCrawlJobResponse cancelCrawl(String id) throws IOException, FirecrawlException {
        return crawlService.cancelCrawlJob(id);
    }

    /**
     * v2: Gets crawl errors.
     */
    public CrawlErrorsResponse getCrawlErrors(String id) throws IOException, FirecrawlException {
        return crawlService.getCrawlErrors(id);
    }

    /**
     * v2: Gets active crawls.
     */
    public CrawlActiveResponse getActiveCrawls() throws IOException, FirecrawlException {
        return crawlService.getActiveCrawls();
    }

    /**
     * v2: Preview crawl parameters derived from a URL and prompt.
     */
    public JsonObject crawlParamsPreview(String url, String prompt) throws IOException, FirecrawlException {
        return crawlService.crawlParamsPreview(url, prompt);
    }

    /**
     * v2: Starts a batch scrape job.
     */
    public BatchScrapeResponse batchScrape(BatchScrapeParams params) throws IOException, FirecrawlException {
        return batchScrapeService.batchScrape(params, null);
    }

    /**
     * v2: Starts a batch scrape job with idempotency key.
     */
    public BatchScrapeResponse batchScrape(BatchScrapeParams params, String idempotencyKey) throws IOException, FirecrawlException {
        return batchScrapeService.batchScrape(params, idempotencyKey);
    }

    /**
     * v2: Gets batch scrape status.
     */
    public BatchScrapeStatusResponse getBatchScrapeStatus(String id) throws IOException, FirecrawlException {
        return batchScrapeService.getBatchScrapeStatus(id);
    }

    /**
     * v2: Gets batch scrape errors.
     */
    public BatchScrapeErrorsResponse getBatchScrapeErrors(String id) throws IOException, FirecrawlException {
        return batchScrapeService.getBatchScrapeErrors(id);
    }

    /**
     * v2: Cancels a batch scrape job.
     */
    public BatchScrapeCancelResponse cancelBatchScrape(String id) throws IOException, FirecrawlException {
        return batchScrapeService.cancelBatchScrape(id);
    }

    /**
     * v2: Creates an agent job.
     */
    public AgentResponse createAgent(AgentParams params) throws IOException, FirecrawlException {
        return agentService.createAgent(params);
    }

    /**
     * v2: Gets agent status.
     */
    public AgentStatusResponse getAgentStatus(String id) throws IOException, FirecrawlException {
        return agentService.getAgentStatus(id);
    }

    /**
     * v2: Cancels an agent job.
     */
    public AgentCancelResponse cancelAgent(String id) throws IOException, FirecrawlException {
        return agentService.cancelAgent(id);
    }

    /**
     * v2: Starts an extract job.
     */
    public ExtractResponse extract(ExtractParams params) throws IOException, FirecrawlException {
        return extractService.extract(params);
    }

    /**
     * v2: Gets extract status.
     */
    public ExtractStatusResponse getExtractStatus(String id) throws IOException, FirecrawlException {
        return extractService.getExtractStatus(id);
    }

    /**
     * v2: Gets credit usage.
     */
    public AccountCreditUsageResponse getCreditUsage() throws IOException, FirecrawlException {
        return accountService.getCreditUsage();
    }

    /**
     * v2: Gets historical credit usage.
     */
    public AccountCreditsHistoricalResponse getCreditUsageHistorical() throws IOException, FirecrawlException {
        return accountService.getCreditUsageHistorical();
    }

    /**
     * v2: Gets token usage.
     */
    public AccountTokenUsageResponse getTokenUsage() throws IOException, FirecrawlException {
        return accountService.getTokenUsage();
    }

    /**
     * v2: Gets historical token usage.
     */
    public AccountTokensHistoricalResponse getTokenUsageHistorical() throws IOException, FirecrawlException {
        return accountService.getTokenUsageHistorical();
    }

    /**
     * v2: Gets queue status.
     */
    public AccountQueueStatusResponse getQueueStatus() throws IOException, FirecrawlException {
        return accountService.getQueueStatus();
    }

    /**
     * Returns the API key.
     *
     * @return the API key
     */
    String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the API URL.
     *
     * @return the API URL
     */
    String getApiUrl() {
        return apiUrl;
    }

    /**
     * Returns the HTTP client.
     *
     * @return the HTTP client
     */
    OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Returns the Gson instance.
     *
     * @return the Gson instance
     */
    Gson getGson() {
        return gson;
    }
}
