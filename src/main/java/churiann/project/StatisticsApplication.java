package churiann.project;

import churiann.project.business.services.ApplicationProcessService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatisticsApplication {

    public static List<String> parseInput(String input) {
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)){
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                result.add(scanner.next());
            }
            return result;
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Invalid arguments");
            System.exit(1);
        }

        String nameOfInputFile = args[0].substring(0, args[0].length() - 1);
        List<String> namesOfStatistics = parseInput(args[1]);
        List<String> namesOfFormats = parseInput(args[2]);

        ApplicationProcessService applicationProcessService = new ApplicationProcessService();
        applicationProcessService.run(nameOfInputFile, namesOfStatistics, namesOfFormats);
    }

}
