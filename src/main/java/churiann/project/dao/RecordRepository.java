package churiann.project.dao;

import churiann.project.domain.Record;

import java.util.List;

public class RecordRepository {
    private List<Record> records;

    public RecordRepository() {
    }

    public RecordRepository(List<Record> records) {
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
