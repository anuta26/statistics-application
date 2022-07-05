package churiann.project.dao;

import churiann.project.domain.Statistics;

import java.util.List;

public class StatisticsRepository {
    private List<Statistics> statistics;

    public StatisticsRepository() {
    }

    public StatisticsRepository(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }
}
