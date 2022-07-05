package churiann.project.business;

import churiann.project.dao.RecordRepository;
import churiann.project.dao.StatisticsRepository;

import java.util.List;

/** Service with main process of application */

public class ApplicationProcess {

    private final RecordRepository recordRepository = new RecordRepository();
    private final StatisticsRepository statisticsRepository = new StatisticsRepository();

    public void run(String nameOfInputFile, List<String> namesOfStatistics, List<String> namesOfFormats) {
        Import anImport = new Import();
        recordRepository.setRecords(anImport.importDataFromFile(nameOfInputFile));
        System.out.println("Records have been imported from file");

        StatisticGenerating statisticGenerating = new StatisticGenerating(recordRepository);
        statisticsRepository.setStatistics(statisticGenerating.makeStatistics(namesOfStatistics));
        System.out.println("All statistics have been generated");

        Export anExport = new Export(statisticsRepository);
        anExport.exportStatistics(namesOfFormats);
        System.out.println("Export has been done");
    }
}

