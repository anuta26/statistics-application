package churiann.project.business.makers;

import churiann.project.domain.Record;
import churiann.project.domain.Statistics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageNumberOfCyclistsPerDayStatisticsMaker implements IStatisticsMaker {

    @Override
    public Statistics makeStatistics(List<Record> records) {
        Map<LocalDate, Integer> days = new HashMap<>();
        for (Record record : records) {
            LocalDate date = record.getStartTime().toLocalDate();
            if (days.containsKey(date)) {
                days.put(date, days.get(date) + record.getNumberOfCyclists());
            } else days.put(date, record.getNumberOfCyclists());
        }

        double averageNumber;
        double summary = 0;
        double totalNumber = 0;
        for (Map.Entry<LocalDate, Integer> entry : days.entrySet()) {
            summary += entry.getValue();
            totalNumber += 1;
        }
        averageNumber = summary / totalNumber;
        return new Statistics("Average number of cyclists passed per day", String.valueOf(averageNumber));
    }
}
