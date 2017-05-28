package il.ac.technion.cs.sd.app;

import java.util.Collections;
import java.util.OptionalInt;

/**
 * This class will only be instantiated after
 * {@link il.ac.technion.cs.sd.app.GradesInitializer#setup(java.lang.String) has been called}.
 */
public class GradesReader {
    private Storage storage;

    public GradesReader() {
        storage = new StorageImpl();
    }

    public GradesReader(Storage storage) {
        this.storage = storage;
    }

    /** Returns the grade associated with the ID, or empty. */
    public OptionalInt getGrade(String id) throws RuntimeException {
        int keyFound;

        try {
            keyFound = Collections.binarySearch(
                    new LazyEvaluationOfStorage(storage)
                    , id
            );
        } catch (RuntimeException e) {
            throw e;
        }

        if (keyFound >= 0) {
            return OptionalInt.of(
                    Integer.parseInt(
                            new Student(storage.read(keyFound))
                                    .getGrade()
                    )
            );
        } else {
            return OptionalInt.empty();
        }
    }
}
