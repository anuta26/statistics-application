package churiann.project.business.export;

import churiann.project.dao.StatisticsRepository;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class JsonExporter implements IExporter {
    @Override
    public void exportStatistics(StatisticsRepository statistics, String directoryToExport) throws IOException {
        FileWriter fWriter = new FileWriter(
                Path.of(directoryToExport, "statistics.json").toAbsolutePath().toString());
        fWriter.write(new Gson().toJson(statistics));
        fWriter.close();
    }
}
