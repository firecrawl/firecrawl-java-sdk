package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a crawl errors request.
 */
public class CrawlErrorsResponse extends BaseResponse {
    private Integer errorCount;
    private JobError[] errors;
    private String[] robotsBlocked;

    public Integer getErrorCount() {
        return errorCount;
    }

    public JobError[] getErrors() {
        return errors;
    }

    public String[] getRobotsBlocked() {
        return robotsBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlErrorsResponse that = (CrawlErrorsResponse) o;
        return Objects.equals(errorCount, that.errorCount) &&
                Arrays.equals(errors, that.errors) &&
                Arrays.equals(robotsBlocked, that.robotsBlocked);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), errorCount);
        result = 31 * result + Arrays.hashCode(errors);
        result = 31 * result + Arrays.hashCode(robotsBlocked);
        return result;
    }

    @Override
    public String toString() {
        return "CrawlErrorsResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", errorCount=" + errorCount +
                ", errors=" + Arrays.toString(errors) +
                ", robotsBlocked=" + Arrays.toString(robotsBlocked) +
                '}';
    }
}
