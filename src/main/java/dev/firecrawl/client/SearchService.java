package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.SearchParams;
import dev.firecrawl.model.SearchResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for search-related API endpoints.
 */
class SearchService extends BaseService {
    /**
     * Creates a new SearchService with the specified client.
     *
     * @param client the FirecrawlClient
     */
    SearchService(FirecrawlClient client) {
        super(client);
    }

    /**
     * Searches for the specified query with the specified parameters.
     *
     * @param params the search parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    SearchResponse search(SearchParams params) throws IOException, FirecrawlException {
        Objects.requireNonNull(params, "SearchParams must not be null");
        
        try {
            params.validate();
        } catch (ValidationException e) {
            throw new FirecrawlException("Invalid search parameters: " + e.getMessage(), e);
        }
        
        JsonObject body = new JsonObject();
        body.addProperty("query", params.getQuery());
        if (params.getLimit() != null) body.addProperty("limit", params.getLimit());
        if (params.getTbs() != null) body.addProperty("tbs", params.getTbs());
        if (params.getLang() != null) body.addProperty("lang", params.getLang());
        if (params.getCountry() != null) body.addProperty("country", params.getCountry());
        if (params.getLocation() != null) body.addProperty("location", params.getLocation());
        if (params.getTimeout() != null) body.addProperty("timeout", params.getTimeout());
        if (params.getIgnoreInvalidURLs() != null) body.addProperty("ignoreInvalidURLs", params.getIgnoreInvalidURLs());
        if (params.getScrapeOptions() != null) body.add("scrapeOptions", gson.toJsonTree(params.getScrapeOptions()));
        if (params.getSourcesAny() != null) {
            body.add("sources", gson.toJsonTree(params.getSourcesAny()));
        } else if (params.getSources() != null) {
            body.add("sources", gson.toJsonTree(params.getSources()));
        }
        if (params.getCategoriesAny() != null) {
            body.add("categories", gson.toJsonTree(params.getCategoriesAny()));
        } else if (params.getCategories() != null) {
            body.add("categories", gson.toJsonTree(params.getCategories()));
        }

        Request request = buildRequest("/v2/search", body);
        return executeRequest(request, SearchResponse.class);
    }

    /**
     * Searches for the specified query with the specified legacy parameters.
     *
     * @param query the search query
     * @param legacyParams the legacy parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     * @deprecated use {@link #search(SearchParams)} instead
     */
    @Deprecated
    SearchResponse search(String query, JsonObject legacyParams) throws IOException, FirecrawlException {
        SearchParams sp = new SearchParams(query);
        if (legacyParams != null) {
            if (legacyParams.has("limit")) sp.setLimit(legacyParams.get("limit").getAsInt());
            if (legacyParams.has("tbs")) sp.setTbs(legacyParams.get("tbs").getAsString());
            if (legacyParams.has("lang")) sp.setLang(legacyParams.get("lang").getAsString());
            if (legacyParams.has("country")) sp.setCountry(legacyParams.get("country").getAsString());
            if (legacyParams.has("location")) sp.setLocation(legacyParams.get("location").getAsString());
            if (legacyParams.has("timeout")) sp.setTimeout(legacyParams.get("timeout").getAsInt());
            if (legacyParams.has("ignoreInvalidURLs")) sp.setIgnoreInvalidURLs(legacyParams.get("ignoreInvalidURLs").getAsBoolean());
            if (legacyParams.has("scrapeOptions")) sp.setScrapeOptions(gson.fromJson(legacyParams.get("scrapeOptions"), dev.firecrawl.model.ScrapeParams.class));
        }
        return search(sp);
    }
}
