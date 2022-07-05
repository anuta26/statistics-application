package churiann.project.business.services;

import churiann.project.dao.RecordRepository;
import churiann.project.dao.StatisticsRepository;

import java.util.List;

/** Service with main process of application */

public class ApplicationProcessService {

    private final RecordRepository recordRepository = new RecordRepository();
    private final StatisticsRepository statisticsRepository = new StatisticsRepository();

    public void run(String nameOfInputFile, List<String> namesOfStatistics, List<String> namesOfFormats) {
        ImportService anImport = new ImportService();
        recordRepository.setRecords(anImport.importDataFromFile(nameOfInputFile));
        System.out.println("Records have been imported from file");

        StatisticGeneratingService statisticGenerating = new StatisticGeneratingService(recordRepository);
        statisticsRepository.setStatistics(statisticGenerating.makeStatistics(namesOfStatistics));
        if (statisticsRepository.getStatistics().isEmpty()) {
            System.out.println("There is not any statistics generated");
            return;
        }
        System.out.println("All statistics have been generated");

        ExportService anExport = new ExportService(statisticsRepository);
        anExport.exportStatistics(namesOfFormats);
        System.out.println("Export has been done");
    }
}

