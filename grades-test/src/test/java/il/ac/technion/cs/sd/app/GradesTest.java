package il.ac.technion.cs.sd.app;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class GradesTest extends SdHw0Test {
    protected static LinkedList<String> fileMock;
    protected static Storage storageMock;

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

    @BeforeClass
    public static void setupStorageMock() {
        fileMock = new LinkedList<>();
        storageMock = Mockito.mock(Storage.class);
        Mockito
                .doAnswer((invocationOnMock -> {fileMock.addLast((String)(invocationOnMock.getArguments()[0])); return null;}))
                .when(storageMock).appendLine(anyString());
        Mockito
                .when(storageMock.read(anyInt()))
                .thenAnswer(invocationOnMock -> fileMock.get((int)invocationOnMock.getArguments()[0]));
        Mockito
                .when(storageMock.numberOfLines())
                .thenAnswer(i -> fileMock.size());
    }

    @After
    public void clear() {
        fileMock.clear();
    }
}
