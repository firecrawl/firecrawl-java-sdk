package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.BatchScrapeCancelResponse;
import dev.firecrawl.model.BatchScrapeErrorsResponse;
import dev.firecrawl.model.BatchScrapeParams;
import dev.firecrawl.model.BatchScrapeResponse;
import dev.firecrawl.model.BatchScrapeStatusResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for batch scrape endpoints.
 */
class BatchScrapeService extends BaseService {

    BatchScrapeService(FirecrawlClient client) {
        super(client);
    }

    BatchScrapeResponse batchScrape(BatchScrapeParams params, String idempotencyKey) throws IOException, FirecrawlException {
        Objects.requireNonNull(params, "BatchScrapeParams must not be null");
        try {
            params.validate();
        } catch (ValidationException e) {
            throw new FirecrawlException("Invalid batch scrape parameters: " + e.getMessage(), e);
        }

        JsonObject body = new JsonObject();
        body.add("urls", gson.toJsonTree(params.getUrls()));
        if (params.getWebhookConfig() != null) {
            body.add("webhook", gson.toJsonTree(params.getWebhookConfig()));
        } else if (params.getWebhook() != null) {
            JsonObject webhook = new JsonObject();
            webhook.addProperty("url", params.getWebhook());
            body.add("webhook", webhook);
        }
        if (params.getMaxConcurrency() != null) body.addProperty("maxConcurrency", params.getMaxConcurrency());
        if (params.getIgnoreInvalidURLs() != null) body.addProperty("ignoreInvalidURLs", params.getIgnoreInvalidURLs());
        if (params.getScrapeOptions() != null) {
            applyScrapeOptions(body, params.getScrapeOptions());
        }

        Request request = buildRequest("/v2/batch/scrape", body, idempotencyKey);
        return executeRequest(request, BatchScrapeResponse.class);
    }

    BatchScrapeStatusResponse getBatchScrapeStatus(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Batch scrape ID must not be null");
        Request request = buildRequest("/v2/batch/scrape/" + id, null, null, "GET");
        return executeRequest(request, BatchScrapeStatusResponse.class);
    }

    BatchScrapeCancelResponse cancelBatchScrape(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Batch scrape ID must not be null");
        Request request = buildRequest("/v2/batch/scrape/" + id, null, null, "DELETE");
        return executeRequest(request, BatchScrapeCancelResponse.class);
    }

    BatchScrapeErrorsResponse getBatchScrapeErrors(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Batch scrape ID must not be null");
        Request request = buildRequest("/v2/batch/scrape/" + id + "/errors", null, null, "GET");
        return executeRequest(request, BatchScrapeErrorsResponse.class);
    }

    private void applyScrapeOptions(JsonObject body, dev.firecrawl.model.ScrapeParams params) {
        if (params.getFormatsAny() != null) body.add("formats", gson.toJsonTree(params.getFormatsAny()));
        else if (params.getFormats() != null) body.add("formats", gson.toJsonTree(params.getFormats()));
        if (params.getHeaders() != null) body.add("headers", gson.toJsonTree(params.getHeaders()));
        if (params.getIncludeTags() != null) body.add("includeTags", gson.toJsonTree(params.getIncludeTags()));
        if (params.getExcludeTags() != null) body.add("excludeTags", gson.toJsonTree(params.getExcludeTags()));
        if (params.getOnlyMainContent() != null) body.addProperty("onlyMainContent", params.getOnlyMainContent());
        if (params.getWaitFor() != null) body.addProperty("waitFor", params.getWaitFor());
        if (params.getParsers() != null) body.add("parsers", gson.toJsonTree(params.getParsers()));
        if (params.getParsePDF() != null) body.addProperty("parsePDF", params.getParsePDF());
        if (params.getTimeout() != null) body.addProperty("timeout", params.getTimeout());
        if (params.getMaxAge() != null) body.addProperty("maxAge", params.getMaxAge());
        if (params.getMobile() != null) body.addProperty("mobile", params.getMobile());
        if (params.getSkipTlsVerification() != null) body.addProperty("skipTlsVerification", params.getSkipTlsVerification());
        if (params.getActions() != null) body.add("actions", gson.toJsonTree(params.getActions()));
        if (params.getLocation() != null) body.add("location", gson.toJsonTree(params.getLocation()));
        if (params.getRemoveBase64Images() != null) body.addProperty("removeBase64Images", params.getRemoveBase64Images());
        if (params.getBlockAds() != null) body.addProperty("blockAds", params.getBlockAds());
        if (params.getProxy() != null) body.addProperty("proxy", params.getProxy());
        if (params.getStoreInCache() != null) body.addProperty("storeInCache", params.getStoreInCache());
        if (params.getZeroDataRetention() != null) body.addProperty("zeroDataRetention", params.getZeroDataRetention());
    }
}
