package dev.firecrawl.model;

import dev.firecrawl.exception.ValidationException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Parameters for batch scrape requests.
 */
public class BatchScrapeParams extends BaseParams<BatchScrapeParams> {
    private String[] urls;
    private ScrapeParams scrapeOptions;
    private String webhook;
    private java.util.Map<String, Object> webhookConfig;
    private Integer maxConcurrency;
    private Boolean ignoreInvalidURLs;

    /**
     * Creates a new BatchScrapeParams instance with required URLs.
     */
    public BatchScrapeParams(String[] urls) {
        this.urls = urls;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (urls == null || urls.length == 0) {
            throw new ValidationException("URLs must not be empty", "urls");
        }
    }

    public String[] getUrls() {
        return urls;
    }

    public BatchScrapeParams setUrls(String[] urls) {
        this.urls = urls;
        return self();
    }

    public ScrapeParams getScrapeOptions() {
        return scrapeOptions;
    }

    public BatchScrapeParams setScrapeOptions(ScrapeParams scrapeOptions) {
        this.scrapeOptions = scrapeOptions;
        return self();
    }

    public String getWebhook() {
        return webhook;
    }

    public BatchScrapeParams setWebhook(String webhook) {
        this.webhook = webhook;
        return self();
    }

    public java.util.Map<String, Object> getWebhookConfig() {
        return webhookConfig;
    }

    public BatchScrapeParams setWebhookConfig(java.util.Map<String, Object> webhookConfig) {
        this.webhookConfig = webhookConfig;
        return self();
    }

    public Integer getMaxConcurrency() {
        return maxConcurrency;
    }

    public BatchScrapeParams setMaxConcurrency(Integer maxConcurrency) {
        this.maxConcurrency = maxConcurrency;
        return self();
    }

    public Boolean getIgnoreInvalidURLs() {
        return ignoreInvalidURLs;
    }

    public BatchScrapeParams setIgnoreInvalidURLs(Boolean ignoreInvalidURLs) {
        this.ignoreInvalidURLs = ignoreInvalidURLs;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BatchScrapeParams that = (BatchScrapeParams) o;
        return Arrays.equals(urls, that.urls) &&
                Objects.equals(scrapeOptions, that.scrapeOptions) &&
                Objects.equals(webhook, that.webhook) &&
                Objects.equals(webhookConfig, that.webhookConfig) &&
                Objects.equals(maxConcurrency, that.maxConcurrency) &&
                Objects.equals(ignoreInvalidURLs, that.ignoreInvalidURLs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), scrapeOptions, webhook, webhookConfig, maxConcurrency, ignoreInvalidURLs);
        result = 31 * result + Arrays.hashCode(urls);
        return result;
    }

    @Override
    public String toString() {
        return "BatchScrapeParams{" +
                "urls=" + Arrays.toString(urls) +
                ", scrapeOptions=" + scrapeOptions +
                ", webhook='" + webhook + '\'' +
                ", webhookConfig=" + webhookConfig +
                ", maxConcurrency=" + maxConcurrency +
                ", ignoreInvalidURLs=" + ignoreInvalidURLs +
                '}';
    }
}
