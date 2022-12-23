package churiann.project.business.exporters;

import churiann.project.dao.StatisticsRepository;
import churiann.project.domain.Statistics;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CsvExporter implements IExporter {
    @Override
    public void exportStatistics(StatisticsRepository statistics, String directoryToExport) throws IOException {
        try (FileWriter outputfile = new FileWriter(
                Path.of(directoryToExport, "statistics.csv").toAbsolutePath().toString())){
            try (CSVWriter writer = new CSVWriter(outputfile)){
                String[] header = {"name of statistics", "result", "time of creation"};
                writer.writeNext(header);
                for (Statistics statistic : statistics.getStatistics()) {
                    String[] newLine = {statistic.getName(), statistic.getResult(), statistic.getTimeOfCreation()};
                    writer.writeNext(newLine);
                }
            }
        }
    }
}
