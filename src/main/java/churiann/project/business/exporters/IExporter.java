package churiann.project.business.exporters;

import churiann.project.dao.StatisticsRepository;

import java.io.IOException;

public interface IExporter {
    void exportStatistics(StatisticsRepository statistics, String directoryToExport) throws IOException;
}
