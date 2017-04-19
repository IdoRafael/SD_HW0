package il.ac.technion.cs.sd.app;


public interface Storage {
    void appendLine(String s);

    String read(int lineNumber);

    int numberOfLines();
}
