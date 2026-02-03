package dev.firecrawl.client;

import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.model.AccountCreditUsageResponse;
import dev.firecrawl.model.AccountCreditsHistoricalResponse;
import dev.firecrawl.model.AccountQueueStatusResponse;
import dev.firecrawl.model.AccountTokenUsageResponse;
import dev.firecrawl.model.AccountTokensHistoricalResponse;
import okhttp3.Request;

import java.io.IOException;

/**
 * Service for account endpoints.
 */
class AccountService extends BaseService {

    AccountService(FirecrawlClient client) {
        super(client);
    }

    AccountCreditUsageResponse getCreditUsage() throws IOException, FirecrawlException {
        Request request = buildRequest("/v2/team/credit-usage", null, null, "GET");
        return executeRequest(request, AccountCreditUsageResponse.class);
    }

    AccountCreditsHistoricalResponse getCreditUsageHistorical() throws IOException, FirecrawlException {
        Request request = buildRequest("/v2/team/credit-usage/historical", null, null, "GET");
        return executeRequest(request, AccountCreditsHistoricalResponse.class);
    }

    AccountTokenUsageResponse getTokenUsage() throws IOException, FirecrawlException {
        Request request = buildRequest("/v2/team/token-usage", null, null, "GET");
        return executeRequest(request, AccountTokenUsageResponse.class);
    }

    AccountTokensHistoricalResponse getTokenUsageHistorical() throws IOException, FirecrawlException {
        Request request = buildRequest("/v2/team/token-usage/historical", null, null, "GET");
        return executeRequest(request, AccountTokensHistoricalResponse.class);
    }

    AccountQueueStatusResponse getQueueStatus() throws IOException, FirecrawlException {
        Request request = buildRequest("/v2/team/queue-status", null, null, "GET");
        return executeRequest(request, AccountQueueStatusResponse.class);
    }
}
