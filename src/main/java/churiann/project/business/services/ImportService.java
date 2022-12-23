package churiann.project.business.services;

import churiann.project.domain.Record;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/** Service takes data from the input file (format : *.csv)
 * and parse them to Records, which would be saved.*/
public class ImportService {

    private static final Logger logger = Logger.getLogger(ImportService.class.getName());

    public List<Record> importDataFromFile(String nameOfInputFile) {
        try {
            List<Record> records = new ArrayList<>();
            FileReader filereader = new FileReader(Path.of(nameOfInputFile).toAbsolutePath().toString());
            try (CSVReader csvReader = new CSVReader(filereader)){
                String[] nextRecord;
                csvReader.readNext();

                DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                while ((nextRecord = csvReader.readNext()) != null) {
                    LocalDateTime startTime = LocalDateTime.parse(nextRecord[2], f);
                    LocalDateTime finishTime = LocalDateTime.parse(nextRecord[3], f);
                    int numberOfCyclists = nextRecord[4].isEmpty() ? 0 : Integer.parseInt(nextRecord[4]);
                    Record record = new Record(
                            nextRecord[0],
                            nextRecord[1],
                            startTime,
                            finishTime,
                            numberOfCyclists
                    );
                    records.add(record);
                }
                return records;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Can not find file");
            System.exit(1);
        } catch (CsvValidationException csvValidationException) {
            logger.error("Wrong format of the input file");
            System.exit(1);
        } catch (IOException ioException) {
            logger.error("Can not read from file");
            System.exit(1);
        }
        return Collections.emptyList();
    }
}
