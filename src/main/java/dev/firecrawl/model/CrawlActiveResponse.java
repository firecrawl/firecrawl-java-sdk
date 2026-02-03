package dev.firecrawl.model;

import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from active crawls endpoint.
 */
public class CrawlActiveResponse extends BaseResponse {
    private CrawlActiveItem[] crawls;

    public CrawlActiveItem[] getCrawls() {
        return crawls;
    }

    public static class CrawlActiveItem {
        private String id;
        private String teamId;
        private String url;
        private String status;
        private CrawlActiveOptions options;
        private String createdAt;
        private String expiresAt;
        private Integer total;
        private Integer completed;
        private Integer creditsUsed;

        public String getId() {
            return id;
        }

        public String getTeamId() {
            return teamId;
        }

        public String getUrl() {
            return url;
        }

        public String getStatus() {
            return status;
        }

        public CrawlActiveOptions getOptions() {
            return options;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getExpiresAt() {
            return expiresAt;
        }

        public Integer getTotal() {
            return total;
        }

        public Integer getCompleted() {
            return completed;
        }

        public Integer getCreditsUsed() {
            return creditsUsed;
        }
    }

    public static class CrawlActiveOptions {
        private String url;
        private JsonElement scrapeOptions;
        private String prompt;
        private String[] excludePaths;
        private String[] includePaths;
        private Integer maxDiscoveryDepth;
        private String sitemap;
        private Boolean ignoreQueryParameters;
        private Integer limit;
        private Boolean crawlEntireDomain;
        private Boolean allowExternalLinks;
        private Boolean allowSubdomains;
        private Integer delay;
        private Integer maxConcurrency;
        private JsonElement webhook;

        public String getUrl() {
            return url;
        }

        public JsonElement getScrapeOptions() {
            return scrapeOptions;
        }

        public String getPrompt() {
            return prompt;
        }

        public String[] getExcludePaths() {
            return excludePaths;
        }

        public String[] getIncludePaths() {
            return includePaths;
        }

        public Integer getMaxDiscoveryDepth() {
            return maxDiscoveryDepth;
        }

        public String getSitemap() {
            return sitemap;
        }

        public Boolean getIgnoreQueryParameters() {
            return ignoreQueryParameters;
        }

        public Integer getLimit() {
            return limit;
        }

        public Boolean getCrawlEntireDomain() {
            return crawlEntireDomain;
        }

        public Boolean getAllowExternalLinks() {
            return allowExternalLinks;
        }

        public Boolean getAllowSubdomains() {
            return allowSubdomains;
        }

        public Integer getDelay() {
            return delay;
        }

        public Integer getMaxConcurrency() {
            return maxConcurrency;
        }

        public JsonElement getWebhook() {
            return webhook;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlActiveResponse that = (CrawlActiveResponse) o;
        return Arrays.equals(crawls, that.crawls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(crawls));
    }

    @Override
    public String toString() {
        return "CrawlActiveResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", crawls=" + Arrays.toString(crawls) +
                '}';
    }
}
