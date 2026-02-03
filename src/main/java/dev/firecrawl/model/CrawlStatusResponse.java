package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a crawl status request.
 */
public class CrawlStatusResponse extends BaseResponse {
    private String status;
    private FirecrawlDocument[] data;
    private Integer total;
    private Integer completed;
    private Integer creditsUsed;
    private String expiresAt;
    private String next;

    /**
     * Returns the status of the crawl job.
     *
     * @return the crawl job status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the crawled documents.
     *
     * @return the crawled documents
     */
    public FirecrawlDocument[] getData() {
        return data;
    }

    /**
     * Returns total items expected.
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Returns completed items count.
     */
    public Integer getCompleted() {
        return completed;
    }

    /**
     * Returns credits used if provided.
     */
    public Integer getCreditsUsed() {
        return creditsUsed;
    }

    /**
     * Returns expiration timestamp if provided.
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Returns the next page cursor if provided.
     */
    public String getNext() {
        return next;
    }

    /**
     * Checks if the crawl job is completed.
     *
     * @return true if the crawl job is completed, false otherwise
     */
    public boolean isCompleted() {
        return "completed".equalsIgnoreCase(status);
    }

    /**
     * Checks if the crawl job is running.
     *
     * @return true if the crawl job is running, false otherwise
     */
    public boolean isRunning() {
        return "running".equalsIgnoreCase(status);
    }

    /**
     * Checks if the crawl job has failed.
     *
     * @return true if the crawl job has failed, false otherwise
     */
    public boolean isFailed() {
        return "failed".equalsIgnoreCase(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlStatusResponse that = (CrawlStatusResponse) o;
        return Objects.equals(status, that.status) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(total, that.total) &&
                Objects.equals(completed, that.completed) &&
                Objects.equals(creditsUsed, that.creditsUsed) &&
                Objects.equals(expiresAt, that.expiresAt) &&
                Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), status);
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + Objects.hashCode(total);
        result = 31 * result + Objects.hashCode(completed);
        result = 31 * result + Objects.hashCode(creditsUsed);
        result = 31 * result + Objects.hashCode(expiresAt);
        result = 31 * result + Objects.hashCode(next);
        return result;
    }

    @Override
    public String toString() {
        return "CrawlStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                ", data=" + Arrays.toString(data) +
                ", total=" + total +
                ", completed=" + completed +
                ", creditsUsed=" + creditsUsed +
                ", expiresAt='" + expiresAt + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
