package dev.firecrawl.model;

import java.util.Objects;

/**
 * Error item for batch/crawl error responses.
 */
public class JobError {
    private String id;
    private String timestamp;
    private String url;
    private Integer status;
    private String error;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobError jobError = (JobError) o;
        return Objects.equals(id, jobError.id) &&
                Objects.equals(timestamp, jobError.timestamp) &&
                Objects.equals(url, jobError.url) &&
                Objects.equals(status, jobError.status) &&
                Objects.equals(error, jobError.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, url, status, error);
    }

    @Override
    public String toString() {
        return "JobError{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}
