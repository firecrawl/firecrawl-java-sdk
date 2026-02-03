package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from an agent creation request.
 */
public class AgentResponse extends BaseResponse {
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AgentResponse that = (AgentResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "AgentResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
