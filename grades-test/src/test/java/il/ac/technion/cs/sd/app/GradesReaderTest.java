package il.ac.technion.cs.sd.app;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class GradesReaderTest extends GradesTest{

    private void shouldFind(int id, OptionalInt grade, String fileName, Storage storageMock) throws FileNotFoundException {
        GradesReader reader = setupInitializerAndGetReader(fileName, storageMock);
        assertEquals(grade, reader.getGrade(String.valueOf(id)));
    }

    @Test
    public void shouldFind123InSmall() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(123, OptionalInt.of(100), "small", storageMock);
    }

    @Test
    public void shouldNotFind1234InSmall() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(1234, OptionalInt.empty(), "small", storageMock);
    }

    @Test
    public void shouldNotFind10000InVeryLarge() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(10000, OptionalInt.empty(), "veryLarge", storageMock);
    }

    @Test
    public void shouldFind123InLarge() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(123, OptionalInt.of(100), "large", storageMock);
    }

    @Test
    public void shouldFind4208InVeryLarge() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(4208, OptionalInt.of(90), "veryLarge", storageMock);
    }

    @Test
    public void shouldFind9999InVeryLarge() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        shouldFind(0, OptionalInt.of(5), "veryLarge", storageMock);
    }

    @Test
    public void maxStudentShouldFinishInTime() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, MAX_STUDENTS)
                .forEach(i -> stringBuilder.append(String.valueOf(i) + "," + String.valueOf(i % 100) + "\n"));
        String csvData = stringBuilder.toString();
        new GradesInitializer(storageMock).setup(csvData);
        GradesReader reader = new GradesReader(storageMock);

        assertEquals(OptionalInt.of((MAX_STUDENTS - 1) % 100), reader.getGrade(String.valueOf(MAX_STUDENTS - 1)));
    }
}
