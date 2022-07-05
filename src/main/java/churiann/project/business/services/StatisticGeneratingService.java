package churiann.project.business.services;

import churiann.project.business.makers.IStatisticsMaker;
import churiann.project.business.makers.StatisticsMakerFactory;
import churiann.project.dao.RecordRepository;
import churiann.project.domain.Statistics;

import java.util.ArrayList;
import java.util.List;

/** Service generates all required statistics, which would be saved. */
public class StatisticGeneratingService {

    private final RecordRepository recordRepository;

    public StatisticGeneratingService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Statistics> makeStatistics(List<String> namesOfStatistics) {
        List<Statistics> statisticsList = new ArrayList<>();
        for (String name : namesOfStatistics) {
            StatisticsMakerFactory statisticsMakerFactory = new StatisticsMakerFactory();
            IStatisticsMaker statisticsMaker = statisticsMakerFactory.createStatisticMaker(name);
            if (statisticsMaker == null) {
                System.out.println("Unknown name of statistics: " + name);
            } else {
                Statistics statistics = statisticsMaker.makeStatistics(recordRepository.getRecords());
                statisticsList.add(statistics);
            }
        }
        return statisticsList;
    }
}
