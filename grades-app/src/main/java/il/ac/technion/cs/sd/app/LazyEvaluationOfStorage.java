package il.ac.technion.cs.sd.app;

import java.util.AbstractList;
import java.util.RandomAccess;

public class LazyEvaluationOfStorage extends AbstractList<String> implements RandomAccess {
    private Storage storage;

    public LazyEvaluationOfStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String get(int index) {
        return new Student(storage.read(index)).getId();
    }

    @Override
    public int size() {
        return storage.numberOfLines();
    }
}
