package churiann.project.business.export;

public class ExporterFactory {
    public IExporter createExporter(String name) {
        return switch (name) {
            case "JSON" -> new JsonExporter();
            case "CSV" -> new CsvExporter();
            default -> null;
        };
    }
}
