package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from token usage endpoint.
 */
public class AccountTokenUsageResponse extends BaseResponse {
    private TokenUsage data;

    public TokenUsage getData() {
        return data;
    }

    public static class TokenUsage {
        private Integer remainingTokens;
        private Integer planTokens;
        private String billingPeriodStart;
        private String billingPeriodEnd;
        private Integer usedTokens;

        public Integer getRemainingTokens() {
            return remainingTokens;
        }

        public Integer getPlanTokens() {
            return planTokens;
        }

        public String getBillingPeriodStart() {
            return billingPeriodStart;
        }

        public String getBillingPeriodEnd() {
            return billingPeriodEnd;
        }

        public Integer getUsedTokens() {
            return usedTokens;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TokenUsage that = (TokenUsage) o;
            return Objects.equals(remainingTokens, that.remainingTokens) &&
                    Objects.equals(planTokens, that.planTokens) &&
                    Objects.equals(billingPeriodStart, that.billingPeriodStart) &&
                    Objects.equals(billingPeriodEnd, that.billingPeriodEnd) &&
                    Objects.equals(usedTokens, that.usedTokens);
        }

        @Override
        public int hashCode() {
            return Objects.hash(remainingTokens, planTokens, billingPeriodStart, billingPeriodEnd, usedTokens);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountTokenUsageResponse that = (AccountTokenUsageResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public String toString() {
        return "AccountTokenUsageResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", data=" + data +
                '}';
    }
}
