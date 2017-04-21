package il.ac.technion.cs.sd.app;

import org.junit.After;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class GradesTest extends SdHw0Test {
    protected static final int MAX_STUDENTS = 1000000;
    protected static final int MAX_LINE_LENGTH = 13;
    protected static final int NUMBER_OF_LINES_SLEEP_DURATION = 100;

    protected static int fileMockSize = 0;
    protected static String[] fileMock = new String[MAX_STUDENTS];
    protected static Storage storageMock = Mockito.mock(Storage.class);;

    protected static String getFileContent(String fileName) throws FileNotFoundException {
        return new Scanner(new File(GradesTest.class.getResource(fileName).getFile())).useDelimiter("\\Z").next();
    }

    protected static void setupInitializer(String fileName) throws FileNotFoundException {
        String fileContents = getFileContent(fileName);
        new GradesInitializer(storageMock).setup(fileContents);
    }

    protected static GradesReader setupInitializerAndGetReader(String fileName) throws FileNotFoundException {
        setupInitializer(fileName);
        return new GradesReader(storageMock);
    }

    protected static GradesReader setupMaxStudentsDatabase() {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, MAX_STUDENTS)
                .forEach(i -> stringBuilder.append(String.valueOf(i) + "," + String.valueOf(i % 100) + "\n"));
        String csvData = stringBuilder.toString();
        new GradesInitializer(storageMock).setup(csvData);
        return new GradesReader(storageMock);
    }

    @BeforeClass
    public static void setupStorageMock() {
        //appendLine
        Mockito
                .doAnswer(
                        invocationOnMock -> {
                            fileMock[fileMockSize] = (String)(invocationOnMock.getArguments()[0]);
                            ++fileMockSize;
                            return null;
                        }
                )
                .when(storageMock).appendLine(anyString());
        //read
        Mockito
                .when(storageMock.read(anyInt()))
                .thenAnswer(
                        invocationOnMock -> {
                            String toReturn = fileMock[(int)invocationOnMock.getArguments()[0]];
                            Thread.sleep(min(toReturn.length(),MAX_LINE_LENGTH));
                            return toReturn;
                        }
                );
        //numberOfLines
        Mockito
                .when(storageMock.numberOfLines())
                .thenAnswer(i -> {Thread.sleep(NUMBER_OF_LINES_SLEEP_DURATION);return fileMockSize;});
    }

    @After
    public void clear() {
        fileMockSize = 0;
    }
}
