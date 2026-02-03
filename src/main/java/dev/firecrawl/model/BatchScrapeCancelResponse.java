package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from cancel batch scrape request.
 */
public class BatchScrapeCancelResponse extends BaseResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BatchScrapeCancelResponse that = (BatchScrapeCancelResponse) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
    }

    @Override
    public String toString() {
        return "BatchScrapeCancelResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
