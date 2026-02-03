package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.AgentCancelResponse;
import dev.firecrawl.model.AgentParams;
import dev.firecrawl.model.AgentResponse;
import dev.firecrawl.model.AgentStatusResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for agent endpoints.
 */
class AgentService extends BaseService {

    AgentService(FirecrawlClient client) {
        super(client);
    }

    AgentResponse createAgent(AgentParams params) throws IOException, FirecrawlException {
        Objects.requireNonNull(params, "AgentParams must not be null");
        try {
            params.validate();
        } catch (ValidationException e) {
            throw new FirecrawlException("Invalid agent parameters: " + e.getMessage(), e);
        }

        JsonObject body = new JsonObject();
        body.addProperty("prompt", params.getPrompt());
        if (params.getUrls() != null) body.add("urls", gson.toJsonTree(params.getUrls()));
        if (params.getSchema() != null) body.add("schema", gson.toJsonTree(params.getSchema()));
        if (params.getMaxCredits() != null) body.addProperty("maxCredits", params.getMaxCredits());
        if (params.getStrictConstrainToUrls() != null) body.addProperty("strictConstrainToURLs", params.getStrictConstrainToUrls());
        if (params.getModel() != null) body.addProperty("model", params.getModel());

        Request request = buildRequest("/v2/agent", body);
        return executeRequest(request, AgentResponse.class);
    }

    AgentStatusResponse getAgentStatus(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Agent ID must not be null");
        Request request = buildRequest("/v2/agent/" + id, null, null, "GET");
        return executeRequest(request, AgentStatusResponse.class);
    }

    AgentCancelResponse cancelAgent(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Agent ID must not be null");
        Request request = buildRequest("/v2/agent/" + id, null, null, "DELETE");
        return executeRequest(request, AgentCancelResponse.class);
    }
}
