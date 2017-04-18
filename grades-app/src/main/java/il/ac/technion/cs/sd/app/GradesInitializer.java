package il.ac.technion.cs.sd.app;

import java.util.AbstractList;
import java.util.Arrays;

/** This class will be instantiated once per test. */
public class GradesInitializer {
    /**
    * Saves the csvData persistently, so that it could be run using GradesRunner.
    * The format of each line of the data is $id,$grade.
    */
    public void setup(String csvData) {
        String[] lines = csvData.split("\\n");
        Storage storage = new Storage();

        //reverse array for distinct to get last grade
        ReversedArrayList<String> reversedLines = new ReversedArrayList<>(lines);

        reversedLines
                .stream()
                .map(x -> new Student(x))
                .distinct()
                .sorted()
                .forEach(s -> storage.appendLine(s.toCSVString()));
    }
}
