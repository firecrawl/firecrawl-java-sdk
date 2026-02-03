package dev.firecrawl.model;

import dev.firecrawl.exception.ValidationException;

import java.util.Objects;

/**
 * Parameters for search requests.
 */
public class SearchParams extends BaseParams<SearchParams> {
    private String query;
    private Integer limit;
    private String tbs;
    private String lang;
    private String country;
    private String location;
    private Integer timeout;
    private Boolean ignoreInvalidURLs;
    private ScrapeParams scrapeOptions;
    // v2: support multiple search sources (e.g., "web", "news", "images")
    private String[] sources;
    // v2: allow object sources (e.g., { type: "web" })
    private Object[] sourcesAny;
    // v2: categories for specialized searches
    private String[] categories;
    private Object[] categoriesAny;

    /**
     * Creates a new SearchParams instance with the required query.
     *
     * @param query the search query
     */
    public SearchParams(String query) {
        this.query = query;
    }

    /**
     * Validates the parameter object.
     *
     * @throws ValidationException if validation fails
     */
    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (query == null || query.trim().isEmpty()) {
            throw new ValidationException("Query must not be empty", "query");
        }
    }

    /**
     * Returns the search query.
     *
     * @return the search query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the search query.
     *
     * @param query the search query
     * @return this instance for method chaining
     */
    public SearchParams setQuery(String query) {
        this.query = query;
        return self();
    }

    /**
     * Returns the maximum number of results to return.
     *
     * @return the maximum number of results
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the maximum number of results to return.
     *
     * @param limit the maximum number of results
     * @return this instance for method chaining
     */
    public SearchParams setLimit(Integer limit) {
        this.limit = limit;
        return self();
    }

    /**
     * Returns the time-based search parameter.
     *
     * @return the time-based search parameter
     */
    public String getTbs() {
        return tbs;
    }

    /**
     * Sets the time-based search parameter.
     *
     * @param tbs the time-based search parameter
     * @return this instance for method chaining
     */
    public SearchParams setTbs(String tbs) {
        this.tbs = tbs;
        return self();
    }

    /**
     * Returns the language code.
     *
     * @return the language code
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the language code.
     *
     * @param lang the language code
     * @return this instance for method chaining
     */
    public SearchParams setLang(String lang) {
        this.lang = lang;
        return self();
    }

    /**
     * Returns the country code.
     *
     * @return the country code
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country code.
     *
     * @param country the country code
     * @return this instance for method chaining
     */
    public SearchParams setCountry(String country) {
        this.country = country;
        return self();
    }

    /**
     * Returns the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the location
     * @return this instance for method chaining
     */
    public SearchParams setLocation(String location) {
        this.location = location;
        return self();
    }

    /**
     * Returns the timeout in seconds.
     *
     * @return the timeout in seconds
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout in seconds.
     *
     * @param timeout the timeout in seconds
     * @return this instance for method chaining
     */
    public SearchParams setTimeout(Integer timeout) {
        this.timeout = timeout;
        return self();
    }

    /**
     * Returns whether to ignore invalid URLs.
     *
     * @return whether to ignore invalid URLs
     */
    public Boolean getIgnoreInvalidURLs() {
        return ignoreInvalidURLs;
    }

    /**
     * Sets whether to ignore invalid URLs.
     *
     * @param ignoreInvalidURLs whether to ignore invalid URLs
     * @return this instance for method chaining
     */
    public SearchParams setIgnoreInvalidURLs(Boolean ignoreInvalidURLs) {
        this.ignoreInvalidURLs = ignoreInvalidURLs;
        return self();
    }

    /**
     * Returns the scrape options.
     *
     * @return the scrape options
     */
    public ScrapeParams getScrapeOptions() {
        return scrapeOptions;
    }

    /**
     * Sets the scrape options.
     *
     * @param scrapeOptions the scrape options
     * @return this instance for method chaining
     */
    public SearchParams setScrapeOptions(ScrapeParams scrapeOptions) {
        this.scrapeOptions = scrapeOptions;
        return self();
    }

    /**
     * v2: Returns the search sources.
     *
     * @return sources
     */
    public String[] getSources() {
        return sources;
    }

    /**
     * v2: Sets the search sources (e.g., "web", "news", "images").
     *
     * @param sources array of sources
     * @return this instance for method chaining
     */
    public SearchParams setSources(String[] sources) {
        this.sources = sources;
        return self();
    }

    /**
     * v2: Returns the search sources allowing object entries.
     */
    public Object[] getSourcesAny() {
        return sourcesAny;
    }

    /**
     * v2: Sets the search sources allowing object entries.
     */
    public SearchParams setSourcesAny(Object[] sourcesAny) {
        this.sourcesAny = sourcesAny;
        return self();
    }

    /**
     * v2: Returns the search categories.
     */
    public String[] getCategories() {
        return categories;
    }

    /**
     * v2: Sets the search categories.
     */
    public SearchParams setCategories(String[] categories) {
        this.categories = categories;
        return self();
    }

    /**
     * v2: Returns the search categories allowing object entries.
     */
    public Object[] getCategoriesAny() {
        return categoriesAny;
    }

    /**
     * v2: Sets the search categories allowing object entries.
     */
    public SearchParams setCategoriesAny(Object[] categoriesAny) {
        this.categoriesAny = categoriesAny;
        return self();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SearchParams that = (SearchParams) o;
        return Objects.equals(query, that.query) &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(tbs, that.tbs) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(country, that.country) &&
                Objects.equals(location, that.location) &&
                Objects.equals(timeout, that.timeout) &&
                Objects.equals(ignoreInvalidURLs, that.ignoreInvalidURLs) &&
                Objects.equals(scrapeOptions, that.scrapeOptions) &&
                java.util.Arrays.equals(sources, that.sources) &&
                java.util.Arrays.equals(sourcesAny, that.sourcesAny) &&
                java.util.Arrays.equals(categories, that.categories) &&
                java.util.Arrays.equals(categoriesAny, that.categoriesAny);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), query, limit, tbs, lang, country, location, timeout, ignoreInvalidURLs, scrapeOptions);
        result = 31 * result + java.util.Arrays.hashCode(sources);
        result = 31 * result + java.util.Arrays.hashCode(sourcesAny);
        result = 31 * result + java.util.Arrays.hashCode(categories);
        result = 31 * result + java.util.Arrays.hashCode(categoriesAny);
        return result;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "query='" + query + '\'' +
                ", limit=" + limit +
                ", tbs='" + tbs + '\'' +
                ", lang='" + lang + '\'' +
                ", country='" + country + '\'' +
                ", location='" + location + '\'' +
                ", timeout=" + timeout +
                ", ignoreInvalidURLs=" + ignoreInvalidURLs +
                ", scrapeOptions=" + scrapeOptions +
                ", sources=" + java.util.Arrays.toString(sources) +
                ", sourcesAny=" + java.util.Arrays.toString(sourcesAny) +
                ", categories=" + java.util.Arrays.toString(categories) +
                ", categoriesAny=" + java.util.Arrays.toString(categoriesAny) +
                '}';
    }
}
