package il.ac.technion.cs.sd.app;


import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LazyEvaluationOfStorageTest extends GradesTest {

    private long getAccessTime(int index) throws FileNotFoundException {
        LazyEvaluationOfStorage lazyEvaluationOfStorage = new LazyEvaluationOfStorage(storageMock);

        long start = System.nanoTime();
        Integer integer = lazyEvaluationOfStorage.get(index);
        return TimeUnit.MILLISECONDS.convert(System.nanoTime()-start, TimeUnit.NANOSECONDS);
    }

    @Test
    public void shouldLazyEvaluateAndNotCallRead() throws Exception {
        setupInitializer("large");
        LazyEvaluationOfStorage lazyEvaluationOfStorage = new LazyEvaluationOfStorage(storageMock);
        Mockito.verify(storageMock, never()).read(anyInt());
    }

    @Test
    public void shouldAccessLastFast() throws Exception {
        setupInitializer("veryLarge");
        assertTrue(getAccessTime(fileMockSize - 1) <= fileMock[fileMockSize - 1].length());
    }

    @Test
    public void shouldAccessRandomlyFast() throws Exception {
        setupInitializer("veryLarge");
        assertTrue(getAccessTime(((new Random()).nextInt(fileMockSize))) <= MAX_LINE_LENGTH);
    }

}
