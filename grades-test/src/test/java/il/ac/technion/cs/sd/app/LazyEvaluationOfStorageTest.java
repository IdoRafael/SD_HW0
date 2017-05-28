package il.ac.technion.cs.sd.app;


import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LazyEvaluationOfStorageTest extends GradesTest {

    private long getAccessTime(int index, Storage storageMock) throws FileNotFoundException {
        LazyEvaluationOfStorage lazyEvaluationOfStorage = new LazyEvaluationOfStorage(storageMock);

        long start = System.nanoTime();
        String string = lazyEvaluationOfStorage.get(index);
        return TimeUnit.MILLISECONDS.convert(System.nanoTime()-start, TimeUnit.NANOSECONDS);
    }

    @Test
    public void shouldLazyEvaluateAndNotCallRead() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        setupInitializer("large", storageMock);
        LazyEvaluationOfStorage lazyEvaluationOfStorage = new LazyEvaluationOfStorage(storageMock);
        Mockito.verify(storageMock, never()).read(anyInt());
    }

    @Test
    public void shouldAccessLastFast() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        setupInitializer("veryLarge", storageMock);
        assertTrue(getAccessTime(fileMock.size() - 1, storageMock) <= fileMock.get(fileMock.size() - 1).length());
    }

    @Test
    public void shouldAccessRandomlyFast() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        setupInitializer("veryLarge", storageMock);
        assertTrue(getAccessTime(((new Random()).nextInt(fileMock.size())), storageMock) <= MAX_LINE_LENGTH);
    }

}
