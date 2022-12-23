package churiann.project.business.services;

import churiann.project.business.exporters.ExporterFactory;
import churiann.project.business.exporters.IExporter;
import churiann.project.dao.StatisticsRepository;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Service allows to export all statistics to files with required formats.
 */
public class ExportService {

    private static final Logger logger = Logger.getLogger(ExportService.class.getName());

    StatisticsRepository statisticsRepository;

    public ExportService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public void exportStatistics(List<String> nameOfFormats) {
        // creation of the directory, where files with statistics would be added
        String directoryToExport = "exportedStatistics";
        File theDir = new File(directoryToExport);
        if (!theDir.exists() && !theDir.mkdirs()) {
            logger.error("Can not create a directory");
            System.exit(1);
        }

        for (String name : nameOfFormats) {
            ExporterFactory exporterFactory = new ExporterFactory();
            IExporter exporter = exporterFactory.createExporter(name);
            if (exporter == null) {
                logger.error("Wrong format of data: " + name);
            } else {
                try {
                    exporter.exportStatistics(statisticsRepository, directoryToExport);
                } catch (IOException e) {
                    logger.error("Can not export data");
                    System.exit(1);
                }
            }
        }
    }
}
