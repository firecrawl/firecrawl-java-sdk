package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from queue status endpoint.
 */
public class AccountQueueStatusResponse extends BaseResponse {
    private Integer jobsInQueue;
    private Integer activeJobsInQueue;
    private Integer waitingJobsInQueue;
    private Integer maxConcurrency;
    private String mostRecentSuccess;

    public Integer getJobsInQueue() {
        return jobsInQueue;
    }

    public Integer getActiveJobsInQueue() {
        return activeJobsInQueue;
    }

    public Integer getWaitingJobsInQueue() {
        return waitingJobsInQueue;
    }

    public Integer getMaxConcurrency() {
        return maxConcurrency;
    }

    public String getMostRecentSuccess() {
        return mostRecentSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountQueueStatusResponse that = (AccountQueueStatusResponse) o;
        return Objects.equals(jobsInQueue, that.jobsInQueue) &&
                Objects.equals(activeJobsInQueue, that.activeJobsInQueue) &&
                Objects.equals(waitingJobsInQueue, that.waitingJobsInQueue) &&
                Objects.equals(maxConcurrency, that.maxConcurrency) &&
                Objects.equals(mostRecentSuccess, that.mostRecentSuccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jobsInQueue, activeJobsInQueue, waitingJobsInQueue, maxConcurrency, mostRecentSuccess);
    }

    @Override
    public String toString() {
        return "AccountQueueStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", jobsInQueue=" + jobsInQueue +
                ", activeJobsInQueue=" + activeJobsInQueue +
                ", waitingJobsInQueue=" + waitingJobsInQueue +
                ", maxConcurrency=" + maxConcurrency +
                ", mostRecentSuccess='" + mostRecentSuccess + '\'' +
                '}';
    }
}
