package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from historical credit usage endpoint.
 */
public class AccountCreditsHistoricalResponse extends BaseResponse {
    private Period[] periods;

    public Period[] getPeriods() {
        return periods;
    }

    public static class Period {
        private String startDate;
        private String endDate;
        private String apiKey;
        private Integer totalCredits;

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getApiKey() {
            return apiKey;
        }

        public Integer getTotalCredits() {
            return totalCredits;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startDate, period.startDate) &&
                    Objects.equals(endDate, period.endDate) &&
                    Objects.equals(apiKey, period.apiKey) &&
                    Objects.equals(totalCredits, period.totalCredits);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, apiKey, totalCredits);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountCreditsHistoricalResponse that = (AccountCreditsHistoricalResponse) o;
        return java.util.Arrays.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), java.util.Arrays.hashCode(periods));
    }

    @Override
    public String toString() {
        return "AccountCreditsHistoricalResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", periods=" + java.util.Arrays.toString(periods) +
                '}';
    }
}
