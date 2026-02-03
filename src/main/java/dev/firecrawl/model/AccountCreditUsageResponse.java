package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from credit usage endpoint.
 */
public class AccountCreditUsageResponse extends BaseResponse {
    private CreditUsage data;

    public CreditUsage getData() {
        return data;
    }

    public static class CreditUsage {
        private Integer remainingCredits;
        private Integer planCredits;
        private String billingPeriodStart;
        private String billingPeriodEnd;
        private Integer totalCredits;
        private Integer usedCredits;
        private Integer creditUsageLast7Days;

        public Integer getRemainingCredits() {
            return remainingCredits;
        }

        public Integer getPlanCredits() {
            return planCredits;
        }

        public String getBillingPeriodStart() {
            return billingPeriodStart;
        }

        public String getBillingPeriodEnd() {
            return billingPeriodEnd;
        }

        public Integer getTotalCredits() {
            return totalCredits;
        }

        public Integer getUsedCredits() {
            return usedCredits;
        }

        public Integer getCreditUsageLast7Days() {
            return creditUsageLast7Days;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreditUsage that = (CreditUsage) o;
            return Objects.equals(remainingCredits, that.remainingCredits) &&
                    Objects.equals(planCredits, that.planCredits) &&
                    Objects.equals(billingPeriodStart, that.billingPeriodStart) &&
                    Objects.equals(billingPeriodEnd, that.billingPeriodEnd) &&
                    Objects.equals(totalCredits, that.totalCredits) &&
                    Objects.equals(usedCredits, that.usedCredits) &&
                    Objects.equals(creditUsageLast7Days, that.creditUsageLast7Days);
        }

        @Override
        public int hashCode() {
            return Objects.hash(remainingCredits, planCredits, billingPeriodStart, billingPeriodEnd,
                    totalCredits, usedCredits, creditUsageLast7Days);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountCreditUsageResponse that = (AccountCreditUsageResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public String toString() {
        return "AccountCreditUsageResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", data=" + data +
                '}';
    }
}
