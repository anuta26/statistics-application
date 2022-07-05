package churiann.project.business.exporters;

public class ExporterFactory {
    public IExporter createExporter(String name) {
        return switch (name) {
            case "JSON" -> new JsonExporter();
            case "CSV" -> new CsvExporter();
            default -> null;
        };
    }
}
