package churiann.project.business.makers;

public class StatisticsMakerFactory {
    public IStatisticsMaker createStatisticMaker(String name) {
        return switch (name) {
            case "DM" -> new DayWithMaximumNumberStatisticsMaker();
            case "MT" -> new MostTraveledSectionStatisticsMaker();
            case "AN" -> new AverageNumberOfCyclistsPerDayStatisticsMaker();
            case "TN" -> new TotalNumberOfCyclistsStatisticsMaker();
            default -> null;
        };
    }
}
