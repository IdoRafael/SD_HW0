package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class GradesReaderTest extends GradesTest{

    private void shouldFind(int id, OptionalInt grade, String fileName) throws FileNotFoundException {
        GradesReader reader = setupInitializerAndGetReader(fileName);
        assertEquals(grade, reader.getGrade(String.valueOf(id)));
    }

    @Test
    public void shouldFind123InSmall() throws Exception {
        shouldFind(123, OptionalInt.of(100), "small");
    }

    @Test
    public void shouldNotFind1234InSmall() throws Exception {
        shouldFind(1234, OptionalInt.empty(), "small");
    }

    @Test
    public void shouldNotFind10000InVeryLarge() throws Exception {
        shouldFind(10000, OptionalInt.empty(), "veryLarge");
    }

    @Test
    public void shouldFind123InLarge() throws Exception {
        shouldFind(123, OptionalInt.of(100), "large");
    }

    @Test
    public void shouldFind4208InVeryLarge() throws Exception {
        shouldFind(4208, OptionalInt.of(90), "veryLarge");
    }

    @Test
    public void shouldFind9999InVeryLarge() throws Exception {
        shouldFind(0, OptionalInt.of(5), "veryLarge");
    }

    @Test
    public void maxStudentShouldFinishInTime() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, MAX_STUDENTS)
                .forEach(i -> stringBuilder.append(String.valueOf(i) + "," + String.valueOf(i % 100) + "\n"));
        String csvData = stringBuilder.toString();
        new GradesInitializer(storageMock).setup(csvData);
        GradesReader reader = new GradesReader(storageMock);

        assertEquals(OptionalInt.of((MAX_STUDENTS - 1) % 100), reader.getGrade(String.valueOf(MAX_STUDENTS - 1)));
    }
}
