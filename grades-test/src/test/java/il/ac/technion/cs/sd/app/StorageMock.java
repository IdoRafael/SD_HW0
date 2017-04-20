package il.ac.technion.cs.sd.app;


import java.util.LinkedList;

/*** for ExampleTest ***/
public class StorageMock implements Storage{
    private LinkedList<String> storage = new LinkedList<>();

    @Override
    public void appendLine(String s) {
        storage.addLast(s);
    }

    @Override
    public String read(int lineNumber) {
        return storage.get(lineNumber);
    }

    @Override
    public int numberOfLines() {
        return storage.size();
    }
}
