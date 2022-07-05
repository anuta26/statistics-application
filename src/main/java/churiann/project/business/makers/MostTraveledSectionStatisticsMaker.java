package churiann.project.business.makers;

import churiann.project.domain.Record;
import churiann.project.domain.Statistics;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostTraveledSectionStatisticsMaker implements IStatisticsMaker {

    @Override
    public Statistics makeStatistics(List<Record> records) {
        String result = "";
        Map<Pair<String, String>, Integer> sections = new HashMap<>();
        for (Record record : records) {
            Pair<String, String> newSection = new ImmutablePair<>(record.getFirstCameraId(), record.getSecondCameraId());
            if (sections.containsKey(newSection)) {
                sections.put(newSection, sections.get(newSection) + record.getNumberOfCyclists());
            } else sections.put(newSection, record.getNumberOfCyclists());
        }

        int max = -1;
        for (Map.Entry<Pair<String, String>, Integer> section : sections.entrySet()) {
            if (max < section.getValue()) {
                max = section.getValue();
                result = section.getKey().getRight() + "to" + section.getKey().getLeft();
            }
        }
        return new Statistics("The most traveled section", result);
    }
}
