package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from an extract request.
 */
public class ExtractResponse extends BaseResponse {
    private String id;
    private String[] invalidURLs;

    public String getId() {
        return id;
    }

    public String[] getInvalidURLs() {
        return invalidURLs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtractResponse that = (ExtractResponse) o;
        return Objects.equals(id, that.id) &&
                Arrays.equals(invalidURLs, that.invalidURLs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id);
        result = 31 * result + Arrays.hashCode(invalidURLs);
        return result;
    }

    @Override
    public String toString() {
        return "ExtractResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", id='" + id + '\'' +
                ", invalidURLs=" + Arrays.toString(invalidURLs) +
                '}';
    }
}
