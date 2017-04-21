package il.ac.technion.cs.sd.app;

import java.util.AbstractList;
import java.util.RandomAccess;

public class LazyEvaluationOfStorage extends AbstractList<Integer> implements RandomAccess {
    private Storage storage;

    public LazyEvaluationOfStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Integer get(int index) {
        return new Student(storage.read(index)).getIntegerId();
    }

    @Override
    public int size() {
        return storage.numberOfLines();
    }
}
