package model.dto;

public class JobDTO {
    private String jobId;
    private String jobName;

    public JobDTO() {
    }

    public JobDTO(String jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
