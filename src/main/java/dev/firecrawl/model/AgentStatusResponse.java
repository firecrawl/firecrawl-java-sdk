package dev.firecrawl.model;

import com.google.gson.JsonElement;

import java.util.Objects;

/**
 * Response from an agent status request.
 */
public class AgentStatusResponse extends BaseResponse {
    private JsonElement data;
    private String status;
    private String expiresAt;

    public JsonElement getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AgentStatusResponse that = (AgentStatusResponse) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(status, that.status) &&
                Objects.equals(expiresAt, that.expiresAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, status, expiresAt);
    }

    @Override
    public String toString() {
        return "AgentStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", data=" + data +
                '}';
    }
}
