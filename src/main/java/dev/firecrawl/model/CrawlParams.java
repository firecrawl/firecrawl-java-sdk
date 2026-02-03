package dev.firecrawl.model;

import java.util.Objects;

/**
 * Parameters for crawl requests.
 */
public class CrawlParams extends BaseParams<CrawlParams> {
    private ScrapeParams scrapeOptions;
    // v2 options
    private String prompt;
    private Boolean crawlEntireDomain;
    private Integer maxDiscoveryDepth;
    private String sitemap; // "only" | "skip" | "include"
    private String[] excludePaths;
    private String[] includePaths;
    private Boolean ignoreQueryParameters;
    private Integer limit;
    private Boolean allowExternalLinks;
    private Boolean allowSubdomains;
    private Integer delay;
    private Integer maxConcurrency;
    private String webhook;
    private java.util.Map<String, Object> webhookConfig;
    private Boolean zeroDataRetention;

    /**
     * Creates a new CrawlParams instance.
     */
    public CrawlParams() {
        // Default constructor
    }

    /**
     * Returns the scrape options for the crawl.
     *
     * @return the scrape options
     */
    public ScrapeParams getScrapeOptions() {
        return scrapeOptions;
    }

    /**
     * Sets the scrape options for the crawl.
     *
     * @param scrapeOptions the scrape options
     * @return this instance for method chaining
     */
    public CrawlParams setScrapeOptions(ScrapeParams scrapeOptions) {
        this.scrapeOptions = scrapeOptions;
        return self();
    }

    /**
     * v2: Returns the natural language prompt used to derive crawl params.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * v2: Sets the prompt.
     */
    public CrawlParams setPrompt(String prompt) {
        this.prompt = prompt;
        return self();
    }

    /**
     * v2: Returns whether to crawl entire domain.
     */
    public Boolean getCrawlEntireDomain() {
        return crawlEntireDomain;
    }

    /**
     * v2: Sets whether to crawl entire domain.
     */
    public CrawlParams setCrawlEntireDomain(Boolean crawlEntireDomain) {
        this.crawlEntireDomain = crawlEntireDomain;
        return self();
    }

    /**
     * v2: Returns the max discovery depth.
     */
    public Integer getMaxDiscoveryDepth() {
        return maxDiscoveryDepth;
    }

    /**
     * v2: Sets the max discovery depth.
     */
    public CrawlParams setMaxDiscoveryDepth(Integer maxDiscoveryDepth) {
        this.maxDiscoveryDepth = maxDiscoveryDepth;
        return self();
    }

    /**
     * v2: Returns sitemap handling ("only" | "skip" | "include").
     */
    public String getSitemap() {
        return sitemap;
    }

    /**
     * v2: Sets sitemap handling.
     */
    public CrawlParams setSitemap(String sitemap) {
        this.sitemap = sitemap;
        return self();
    }

    /**
     * v2: Returns the exclude paths list.
     */
    public String[] getExcludePaths() {
        return excludePaths;
    }

    /**
     * v2: Sets the exclude paths list.
     */
    public CrawlParams setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
        return self();
    }

    /**
     * v2: Returns the include paths list.
     */
    public String[] getIncludePaths() {
        return includePaths;
    }

    /**
     * v2: Sets the include paths list.
     */
    public CrawlParams setIncludePaths(String[] includePaths) {
        this.includePaths = includePaths;
        return self();
    }

    /**
     * v2: Returns whether to ignore query parameters.
     */
    public Boolean getIgnoreQueryParameters() {
        return ignoreQueryParameters;
    }

    /**
     * v2: Sets whether to ignore query parameters.
     */
    public CrawlParams setIgnoreQueryParameters(Boolean ignoreQueryParameters) {
        this.ignoreQueryParameters = ignoreQueryParameters;
        return self();
    }

    /**
     * v2: Returns the max number of pages to crawl.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * v2: Sets the max number of pages to crawl.
     */
    public CrawlParams setLimit(Integer limit) {
        this.limit = limit;
        return self();
    }

    /**
     * v2: Returns whether to allow external links.
     */
    public Boolean getAllowExternalLinks() {
        return allowExternalLinks;
    }

    /**
     * v2: Sets whether to allow external links.
     */
    public CrawlParams setAllowExternalLinks(Boolean allowExternalLinks) {
        this.allowExternalLinks = allowExternalLinks;
        return self();
    }

    /**
     * v2: Returns whether to allow subdomains.
     */
    public Boolean getAllowSubdomains() {
        return allowSubdomains;
    }

    /**
     * v2: Sets whether to allow subdomains.
     */
    public CrawlParams setAllowSubdomains(Boolean allowSubdomains) {
        this.allowSubdomains = allowSubdomains;
        return self();
    }

    /**
     * v2: Returns delay between requests (ms).
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * v2: Sets delay between requests (ms).
     */
    public CrawlParams setDelay(Integer delay) {
        this.delay = delay;
        return self();
    }

    /**
     * v2: Returns max concurrency.
     */
    public Integer getMaxConcurrency() {
        return maxConcurrency;
    }

    /**
     * v2: Sets max concurrency.
     */
    public CrawlParams setMaxConcurrency(Integer maxConcurrency) {
        this.maxConcurrency = maxConcurrency;
        return self();
    }

    /**
     * v2: Returns webhook URL.
     */
    public String getWebhook() {
        return webhook;
    }

    /**
     * v2: Sets webhook URL.
     */
    public CrawlParams setWebhook(String webhook) {
        this.webhook = webhook;
        return self();
    }

    /**
     * v2: Returns the webhook configuration object if provided.
     */
    public java.util.Map<String, Object> getWebhookConfig() {
        return webhookConfig;
    }

    /**
     * v2: Sets the webhook configuration object.
     */
    public CrawlParams setWebhookConfig(java.util.Map<String, Object> webhookConfig) {
        this.webhookConfig = webhookConfig;
        return self();
    }

    /**
     * v2: Returns whether zero data retention is enabled.
     */
    public Boolean getZeroDataRetention() {
        return zeroDataRetention;
    }

    /**
     * v2: Sets zero data retention.
     */
    public CrawlParams setZeroDataRetention(Boolean zeroDataRetention) {
        this.zeroDataRetention = zeroDataRetention;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlParams that = (CrawlParams) o;
        return Objects.equals(scrapeOptions, that.scrapeOptions) &&
                Objects.equals(prompt, that.prompt) &&
                Objects.equals(crawlEntireDomain, that.crawlEntireDomain) &&
                Objects.equals(maxDiscoveryDepth, that.maxDiscoveryDepth) &&
                Objects.equals(sitemap, that.sitemap) &&
                java.util.Arrays.equals(excludePaths, that.excludePaths) &&
                java.util.Arrays.equals(includePaths, that.includePaths) &&
                Objects.equals(ignoreQueryParameters, that.ignoreQueryParameters) &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(allowExternalLinks, that.allowExternalLinks) &&
                Objects.equals(allowSubdomains, that.allowSubdomains) &&
                Objects.equals(delay, that.delay) &&
                Objects.equals(maxConcurrency, that.maxConcurrency) &&
                Objects.equals(webhook, that.webhook) &&
                Objects.equals(webhookConfig, that.webhookConfig) &&
                Objects.equals(zeroDataRetention, that.zeroDataRetention);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), scrapeOptions, prompt, crawlEntireDomain, maxDiscoveryDepth, sitemap,
                ignoreQueryParameters, limit, allowExternalLinks, allowSubdomains, delay, maxConcurrency, webhook, webhookConfig, zeroDataRetention);
        result = 31 * result + java.util.Arrays.hashCode(excludePaths);
        result = 31 * result + java.util.Arrays.hashCode(includePaths);
        return result;
    }

    @Override
    public String toString() {
        return "CrawlParams{" +
                "scrapeOptions=" + scrapeOptions +
                ", prompt='" + prompt + '\'' +
                ", crawlEntireDomain=" + crawlEntireDomain +
                ", maxDiscoveryDepth=" + maxDiscoveryDepth +
                ", sitemap='" + sitemap + '\'' +
                ", excludePaths=" + java.util.Arrays.toString(excludePaths) +
                ", includePaths=" + java.util.Arrays.toString(includePaths) +
                ", ignoreQueryParameters=" + ignoreQueryParameters +
                ", limit=" + limit +
                ", allowExternalLinks=" + allowExternalLinks +
                ", allowSubdomains=" + allowSubdomains +
                ", delay=" + delay +
                ", maxConcurrency=" + maxConcurrency +
                ", webhook='" + webhook + '\'' +
                ", webhookConfig=" + webhookConfig +
                ", zeroDataRetention=" + zeroDataRetention +
                '}';
    }
}
