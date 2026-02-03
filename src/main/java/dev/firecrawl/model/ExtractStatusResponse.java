package dev.firecrawl.model;

import com.google.gson.JsonElement;

import java.util.Objects;

/**
 * Response from an extract status request.
 */
public class ExtractStatusResponse extends BaseResponse {
    private JsonElement data;
    private String status;
    private String expiresAt;
    private Integer tokensUsed;

    public JsonElement getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public Integer getTokensUsed() {
        return tokensUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtractStatusResponse that = (ExtractStatusResponse) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(status, that.status) &&
                Objects.equals(expiresAt, that.expiresAt) &&
                Objects.equals(tokensUsed, that.tokensUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, status, expiresAt, tokensUsed);
    }

    @Override
    public String toString() {
        return "ExtractStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", tokensUsed=" + tokensUsed +
                ", data=" + data +
                '}';
    }
}
