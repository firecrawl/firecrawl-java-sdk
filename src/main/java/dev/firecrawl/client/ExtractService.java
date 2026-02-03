package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.ExtractParams;
import dev.firecrawl.model.ExtractResponse;
import dev.firecrawl.model.ExtractStatusResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for extract endpoints.
 */
class ExtractService extends BaseService {

    ExtractService(FirecrawlClient client) {
        super(client);
    }

    ExtractResponse extract(ExtractParams params) throws IOException, FirecrawlException {
        Objects.requireNonNull(params, "ExtractParams must not be null");
        try {
            params.validate();
        } catch (ValidationException e) {
            throw new FirecrawlException("Invalid extract parameters: " + e.getMessage(), e);
        }

        JsonObject body = new JsonObject();
        body.add("urls", gson.toJsonTree(params.getUrls()));
        if (params.getPrompt() != null) body.addProperty("prompt", params.getPrompt());
        if (params.getSchema() != null) body.add("schema", gson.toJsonTree(params.getSchema()));
        if (params.getEnableWebSearch() != null) body.addProperty("enableWebSearch", params.getEnableWebSearch());
        if (params.getIgnoreSitemap() != null) body.addProperty("ignoreSitemap", params.getIgnoreSitemap());
        if (params.getIncludeSubdomains() != null) body.addProperty("includeSubdomains", params.getIncludeSubdomains());
        if (params.getShowSources() != null) body.addProperty("showSources", params.getShowSources());
        if (params.getScrapeOptions() != null) body.add("scrapeOptions", gson.toJsonTree(params.getScrapeOptions()));
        if (params.getIgnoreInvalidURLs() != null) body.addProperty("ignoreInvalidURLs", params.getIgnoreInvalidURLs());

        Request request = buildRequest("/v2/extract", body);
        return executeRequest(request, ExtractResponse.class);
    }

    ExtractStatusResponse getExtractStatus(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Extract ID must not be null");
        Request request = buildRequest("/v2/extract/" + id, null, null, "GET");
        return executeRequest(request, ExtractStatusResponse.class);
    }
}
