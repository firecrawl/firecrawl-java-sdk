package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a batch scrape request.
 */
public class BatchScrapeResponse extends BaseResponse {
    private String id;
    private String url;
    private String[] invalidURLs;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String[] getInvalidURLs() {
        return invalidURLs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BatchScrapeResponse that = (BatchScrapeResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(url, that.url) &&
                Arrays.equals(invalidURLs, that.invalidURLs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id, url);
        result = 31 * result + Arrays.hashCode(invalidURLs);
        return result;
    }

    @Override
    public String toString() {
        return "BatchScrapeResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", invalidURLs=" + Arrays.toString(invalidURLs) +
                '}';
    }
}
