package il.ac.technion.cs.sd.app;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.OptionalInt;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;


public class GradesInitializerTest {
    private static LinkedList<String> fileMock;
    private static Storage storageMock;

    @Rule public Timeout globalTimeout = Timeout.seconds(10);


    private static GradesReader setupAndGetReader(String fileName) throws FileNotFoundException {
        String fileContents =
                new Scanner(new File(ExampleTest.class.getResource(fileName).getFile())).useDelimiter("\\Z").next();
        new GradesInitializer(storageMock).setup(fileContents);
        return new GradesReader(storageMock);
    }

    @BeforeClass
    public static void setupStorageMock() {
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

        System.out.println("BeforeClass");
    }

    @Before
    public void setUp() {
        fileMock = new LinkedList<>();
        System.out.println("Before");
    }

    @Test
    public void testSimple() throws Exception {
        GradesReader reader = setupAndGetReader("small");
        assertEquals(OptionalInt.of(100), reader.getGrade("123"));
    }

    @Test
    public void testEmpty() throws Exception {
        GradesReader reader = setupAndGetReader("small");
        assertEquals(OptionalInt.empty(), reader.getGrade("1234"));
    }

    @Test
    public void largeTest() throws Exception {
        GradesReader reader = setupAndGetReader("large");
        assertEquals(OptionalInt.of(100), reader.getGrade("123"));
    }

    @Test
    public void test() throws Exception {
        assertTrue(true);
    }
}
