package churiann.project.domain;

import java.time.LocalDateTime;

public class Statistics {
    private String name;
    private String result;
    private String timeOfCreation;

    public Statistics() {
    }

    public Statistics(String name, String result) {
        this.name = name;
        this.result = result;
        this.timeOfCreation = LocalDateTime.now().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(String timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "name='" + name + '\'' +
                ", result='" + result + '\'' +
                ", timeOfCreation=" + timeOfCreation +
                '}';
    }
}
