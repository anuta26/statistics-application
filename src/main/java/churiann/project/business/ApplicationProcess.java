package churiann.project.business;

import churiann.project.business.export.IExporter;
import churiann.project.business.export.ExporterFactory;
import churiann.project.business.makers.IStatisticsMaker;
import churiann.project.business.makers.StatisticsMakerFactory;
import churiann.project.dao.RecordRepository;
import churiann.project.dao.StatisticsRepository;
import churiann.project.domain.Record;
import churiann.project.domain.Statistics;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/* Service with main process of application */

public class ApplicationProcess {

    private final RecordRepository recordRepository = new RecordRepository();
    private final StatisticsRepository statisticsRepository = new StatisticsRepository();

    public void run(String nameOfInputFile, List<String> namesOfStatistics, List<String> namesOfFormats) {
        importDataFromFile(nameOfInputFile);
        makeStatistics(namesOfStatistics);
        exportStatistics(namesOfFormats);
    }

    /* function takes data from the input file (format : *.csv)
    and parse them to Records, which would be saved into recordRepository
    */
    public void importDataFromFile(String nameOfInputFile) {
        try {
            List<Record> records = new ArrayList<>();

            FileReader filereader = new FileReader(Path.of(nameOfInputFile).toAbsolutePath().toString());
            CSVReader csvReader = new CSVReader(filereader);
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
            recordRepository.setRecords(records);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Can not find file");
            System.exit(1);
        } catch (CsvValidationException csvValidationException) {
            System.err.println("Wrong format of the input file");
            System.exit(1);
        } catch (IOException ioException) {
            System.err.println("Can not read from file");
            System.exit(1);
        }
    }

    /* function makes all Statistics, which are required, using Factory method with StatisticsMaker
     and  save them into statisticRepository
     */
    public void makeStatistics(List<String> namesOfStatistics) {
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
        statisticsRepository.setStatistics(statisticsList);
    }

    /* function allow to export all statistics to files with required formats using Factory method with Exporter */
    public void exportStatistics(List<String> nameOfFormats) {

        /* creation of the directory, where files with statistics would be added */
        String directoryToExport = "exportedStatistics";
        File theDir = new File(directoryToExport);
        if (!theDir.exists()) {
            if (!theDir.mkdirs()) {
                System.err.println("Can not create a directory");
                System.exit(1);
            }
        }

        for (String name : nameOfFormats) {
            ExporterFactory exporterFactory = new ExporterFactory();
            IExporter exporter = exporterFactory.createExporter(name);
            if (exporter == null) {
                System.out.println("Wrong format of data: " + name);
            } else {
                try {
                    exporter.exportStatistics(statisticsRepository, directoryToExport);
                } catch (IOException e) {
                    System.err.println("Can not export data");
                    System.exit(1);
                }
            }
        }
    }

}

