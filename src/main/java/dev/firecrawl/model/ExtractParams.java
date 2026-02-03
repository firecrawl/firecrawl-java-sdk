package dev.firecrawl.model;

import dev.firecrawl.exception.ValidationException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Parameters for extract requests.
 */
public class ExtractParams extends BaseParams<ExtractParams> {
    private String[] urls;
    private String prompt;
    private Map<String, Object> schema;
    private Boolean enableWebSearch;
    private Boolean ignoreSitemap;
    private Boolean includeSubdomains;
    private Boolean showSources;
    private ScrapeParams scrapeOptions;
    private Boolean ignoreInvalidURLs;

    public ExtractParams(String[] urls) {
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

    public ExtractParams setUrls(String[] urls) {
        this.urls = urls;
        return self();
    }

    public String getPrompt() {
        return prompt;
    }

    public ExtractParams setPrompt(String prompt) {
        this.prompt = prompt;
        return self();
    }

    public Map<String, Object> getSchema() {
        return schema;
    }

    public ExtractParams setSchema(Map<String, Object> schema) {
        this.schema = schema;
        return self();
    }

    public Boolean getEnableWebSearch() {
        return enableWebSearch;
    }

    public ExtractParams setEnableWebSearch(Boolean enableWebSearch) {
        this.enableWebSearch = enableWebSearch;
        return self();
    }

    public Boolean getIgnoreSitemap() {
        return ignoreSitemap;
    }

    public ExtractParams setIgnoreSitemap(Boolean ignoreSitemap) {
        this.ignoreSitemap = ignoreSitemap;
        return self();
    }

    public Boolean getIncludeSubdomains() {
        return includeSubdomains;
    }

    public ExtractParams setIncludeSubdomains(Boolean includeSubdomains) {
        this.includeSubdomains = includeSubdomains;
        return self();
    }

    public Boolean getShowSources() {
        return showSources;
    }

    public ExtractParams setShowSources(Boolean showSources) {
        this.showSources = showSources;
        return self();
    }

    public ScrapeParams getScrapeOptions() {
        return scrapeOptions;
    }

    public ExtractParams setScrapeOptions(ScrapeParams scrapeOptions) {
        this.scrapeOptions = scrapeOptions;
        return self();
    }

    public Boolean getIgnoreInvalidURLs() {
        return ignoreInvalidURLs;
    }

    public ExtractParams setIgnoreInvalidURLs(Boolean ignoreInvalidURLs) {
        this.ignoreInvalidURLs = ignoreInvalidURLs;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtractParams that = (ExtractParams) o;
        return Arrays.equals(urls, that.urls) &&
                Objects.equals(prompt, that.prompt) &&
                Objects.equals(schema, that.schema) &&
                Objects.equals(enableWebSearch, that.enableWebSearch) &&
                Objects.equals(ignoreSitemap, that.ignoreSitemap) &&
                Objects.equals(includeSubdomains, that.includeSubdomains) &&
                Objects.equals(showSources, that.showSources) &&
                Objects.equals(scrapeOptions, that.scrapeOptions) &&
                Objects.equals(ignoreInvalidURLs, that.ignoreInvalidURLs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), prompt, schema, enableWebSearch, ignoreSitemap, includeSubdomains, showSources, scrapeOptions, ignoreInvalidURLs);
        result = 31 * result + Arrays.hashCode(urls);
        return result;
    }

    @Override
    public String toString() {
        return "ExtractParams{" +
                "urls=" + Arrays.toString(urls) +
                ", prompt='" + prompt + '\'' +
                ", schema=" + schema +
                ", enableWebSearch=" + enableWebSearch +
                ", ignoreSitemap=" + ignoreSitemap +
                ", includeSubdomains=" + includeSubdomains +
                ", showSources=" + showSources +
                ", scrapeOptions=" + scrapeOptions +
                ", ignoreInvalidURLs=" + ignoreInvalidURLs +
                '}';
    }
}
