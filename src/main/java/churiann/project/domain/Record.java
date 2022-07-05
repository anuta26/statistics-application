package churiann.project.domain;

import java.time.LocalDateTime;

public class Record {
    private String firstCameraId;
    private String secondCameraId;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int numberOfCyclists;

    public Record(String firstCameraId, String secondCameraId, LocalDateTime startTime, LocalDateTime finishTime, int numberOfCyclists) {
        this.firstCameraId = firstCameraId;
        this.secondCameraId = secondCameraId;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.numberOfCyclists = numberOfCyclists;
    }

    public String getFirstCameraId() {
        return firstCameraId;
    }

    public void setFirstCameraId(String firstCameraId) {
        this.firstCameraId = firstCameraId;
    }

    public String getSecondCameraId() {
        return secondCameraId;
    }

    public void setSecondCameraId(String secondCameraId) {
        this.secondCameraId = secondCameraId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public int getNumberOfCyclists() {
        return numberOfCyclists;
    }

    public void setNumberOfCyclists(int numberOfCyclists) {
        this.numberOfCyclists = numberOfCyclists;
    }

    @Override
    public String toString() {
        return "Record{" +
                "firstCameraId='" + firstCameraId + '\'' +
                ", secondCameraId='" + secondCameraId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", numberOfCyclists=" + numberOfCyclists +
                '}';
    }
}
