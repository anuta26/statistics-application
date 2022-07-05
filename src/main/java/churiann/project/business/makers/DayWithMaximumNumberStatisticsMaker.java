package churiann.project.business.makers;

import churiann.project.domain.Record;
import churiann.project.domain.Statistics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayWithMaximumNumberStatisticsMaker implements IStatisticsMaker {
    @Override
    public Statistics makeStatistics(List<Record> records) {
        String result = "";
        Map<LocalDate, Integer> days = new HashMap<>();
        for (Record record : records) {
            LocalDate date = record.getStartTime().toLocalDate();
            if (days.containsKey(date)) {
                days.put(date, days.get(date) + record.getNumberOfCyclists());
            } else days.put(date, record.getNumberOfCyclists());
        }

        int max = -1;
        for (Map.Entry<LocalDate, Integer> entry : days.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                result = entry.getKey().toString();
            }
        }

        return new Statistics("The most popular day (with maximum number of cyclists passed) ", result);
    }
}
