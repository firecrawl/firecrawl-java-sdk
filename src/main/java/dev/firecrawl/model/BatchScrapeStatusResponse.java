package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a batch scrape status request.
 */
public class BatchScrapeStatusResponse extends BaseResponse {
    private String status;
    private Integer total;
    private Integer completed;
    private Integer creditsUsed;
    private String expiresAt;
    private String next;
    private FirecrawlDocument[] data;

    public String getStatus() {
        return status;
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

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getNext() {
        return next;
    }

    public FirecrawlDocument[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BatchScrapeStatusResponse that = (BatchScrapeStatusResponse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(total, that.total) &&
                Objects.equals(completed, that.completed) &&
                Objects.equals(creditsUsed, that.creditsUsed) &&
                Objects.equals(expiresAt, that.expiresAt) &&
                Objects.equals(next, that.next) &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), status, total, completed, creditsUsed, expiresAt, next);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "BatchScrapeStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", completed=" + completed +
                ", creditsUsed=" + creditsUsed +
                ", expiresAt='" + expiresAt + '\'' +
                ", next='" + next + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
