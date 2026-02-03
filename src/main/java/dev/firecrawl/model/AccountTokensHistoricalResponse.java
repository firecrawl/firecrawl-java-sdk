package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from historical token usage endpoint.
 */
public class AccountTokensHistoricalResponse extends BaseResponse {
    private Period[] periods;

    public Period[] getPeriods() {
        return periods;
    }

    public static class Period {
        private String startDate;
        private String endDate;
        private String apiKey;
        private Integer totalTokens;

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getApiKey() {
            return apiKey;
        }

        public Integer getTotalTokens() {
            return totalTokens;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startDate, period.startDate) &&
                    Objects.equals(endDate, period.endDate) &&
                    Objects.equals(apiKey, period.apiKey) &&
                    Objects.equals(totalTokens, period.totalTokens);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, apiKey, totalTokens);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountTokensHistoricalResponse that = (AccountTokensHistoricalResponse) o;
        return java.util.Arrays.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), java.util.Arrays.hashCode(periods));
    }

    @Override
    public String toString() {
        return "AccountTokensHistoricalResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", periods=" + java.util.Arrays.toString(periods) +
                '}';
    }
}
