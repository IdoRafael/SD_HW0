package il.ac.technion.cs.sd.app;


/** This class will be instantiated once per test. */
public class GradesInitializer {
    private Storage storage;

    public GradesInitializer() {
        storage = new StorageImpl();
    }

    public GradesInitializer(Storage storage) {
        this.storage = storage;
    }

    /**
    * Saves the csvData persistently, so that it could be run using GradesRunner.
    * The format of each line of the data is $id,$grade.
    */
    public void setup(String csvData) {
        String[] lines = csvData.split("\\n");

        //reverse array for distinct to get last grade instead of first
        ReversedArrayList<String> reversedLines = new ReversedArrayList<>(lines);

        reversedLines
                .stream()
                .map(x -> new Student(x))
                .distinct()
                .sorted()
                .forEach(s -> storage.appendLine(s.toCSVString()));
    }
}
