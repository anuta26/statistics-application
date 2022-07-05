package churiann.project.business.makers;

import churiann.project.domain.Record;
import churiann.project.domain.Statistics;

import java.util.List;

public interface IStatisticsMaker {
    Statistics makeStatistics(List<Record> records);
}
