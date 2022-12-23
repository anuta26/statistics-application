package churiann.project.business.services;

import churiann.project.dao.RecordRepository;
import churiann.project.dao.StatisticsRepository;
import org.apache.log4j.Logger;

import java.util.List;

/** Service with main process of application */

public class ApplicationProcessService {

    private static final Logger logger = Logger.getLogger(ApplicationProcessService.class.getName());

    private final RecordRepository recordRepository = new RecordRepository();
    private final StatisticsRepository statisticsRepository = new StatisticsRepository();

    public void run(String nameOfInputFile, List<String> namesOfStatistics, List<String> namesOfFormats) {
        ImportService anImport = new ImportService();
        recordRepository.setRecords(anImport.importDataFromFile(nameOfInputFile));
        logger.info("Records have been imported from file");

        StatisticGeneratingService statisticGenerating = new StatisticGeneratingService(recordRepository);
        statisticsRepository.setStatistics(statisticGenerating.makeStatistics(namesOfStatistics));
        if (statisticsRepository.getStatistics().isEmpty()) {
            logger.warn("There is not any statistics generated");
            return;
        }
        logger.info("All statistics have been generated");

        ExportService anExport = new ExportService(statisticsRepository);
        anExport.exportStatistics(namesOfFormats);
        logger.info("Export has been done");
    }
}

