package il.ac.technion.cs.sd.app;


import il.ac.technion.cs.sd.grades.ext.LineStorage;

public class Storage {
    public void appendLine(String s) {
        LineStorage.appendLine(s);
    }

    public String read(int lineNumber) throws RuntimeException {
        try {
            return LineStorage.read(lineNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int numberOfLines() throws RuntimeException {
        try {
            return LineStorage.numberOfLines();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
