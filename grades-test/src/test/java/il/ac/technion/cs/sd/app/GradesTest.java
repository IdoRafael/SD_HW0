package il.ac.technion.cs.sd.app;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class GradesTest extends SdHw0Test {
    protected static final int MAX_STUDENTS = 1000000;
    protected static final int MAX_LINE_LENGTH = 13;
    protected static final int NUMBER_OF_LINES_SLEEP_DURATION = 100;


    protected static String getFileContent(String fileName) throws FileNotFoundException {
        return new Scanner(new File(GradesTest.class.getResource(fileName).getFile())).useDelimiter("\\Z").next();
    }

    protected static void setupInitializer(String fileName, Storage storageMock) throws FileNotFoundException {
        String fileContents = getFileContent(fileName);
        new GradesInitializer(storageMock).setup(fileContents);
    }

    protected static GradesReader setupInitializerAndGetReader(String fileName, Storage storageMock) throws FileNotFoundException {
        setupInitializer(fileName, storageMock);
        return new GradesReader(storageMock);
    }

    protected static void setupStorageMock(ArrayList<String> fileMock, Storage storageMock) {
        //appendLine
        Mockito
                .doAnswer(
                        invocationOnMock -> {
                            fileMock.add((String)(invocationOnMock.getArguments()[0]));
                            return null;
                        }
                )
                .when(storageMock).appendLine(anyString());
        //read
        Mockito
                .when(storageMock.read(anyInt()))
                .thenAnswer(
                        invocationOnMock -> {
                            String toReturn = fileMock.get((int)invocationOnMock.getArguments()[0]);
                            Thread.sleep(min(toReturn.length(),MAX_LINE_LENGTH));
                            return toReturn;
                        }
                );
        //numberOfLines
        Mockito
                .when(storageMock.numberOfLines())
                .thenAnswer(
                        i -> {
                            Thread.sleep(NUMBER_OF_LINES_SLEEP_DURATION);
                            return fileMock.size();
                        }
                );
    }
}
