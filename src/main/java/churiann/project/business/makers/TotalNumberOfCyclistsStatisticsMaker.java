package churiann.project.business.makers;

import churiann.project.domain.Record;
import churiann.project.domain.Statistics;

import java.util.List;

public class TotalNumberOfCyclistsStatisticsMaker implements IStatisticsMaker {

    @Override
    public Statistics makeStatistics(List<Record> records) {
        int totalNumberOfCyclists = 0;
        for (Record record : records) {
            totalNumberOfCyclists += record.getNumberOfCyclists();
        }
        return new Statistics(
                "the total number of recorded cyclist passes",
                String.valueOf(totalNumberOfCyclists));
    }
}
