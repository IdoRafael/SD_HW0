package il.ac.technion.cs.sd.app;


import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

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
        SortedMap<Integer, Student> sortedStudentsMap = new TreeMap<>();

        Arrays.stream(lines)
                .map(x -> new Student(x))
                .forEach(s -> sortedStudentsMap.put(s.getIntegerId(), s));

        sortedStudentsMap.forEach((id, student) -> storage.appendLine(student.toCSVString()));
    }
}
