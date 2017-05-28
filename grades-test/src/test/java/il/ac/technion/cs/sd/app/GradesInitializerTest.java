package il.ac.technion.cs.sd.app;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GradesInitializerTest extends GradesTest {

    @Test
    public void shouldRemoveDuplicates() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        setupInitializer("duplicates", storageMock);

        assertEquals(1, fileMock.size());
        assertEquals("123", new Student(fileMock.get(0)).getId());
    }

    @Test
    public void shouldLeaveLastGrade() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);

        setupInitializer("duplicates", storageMock);

        assertEquals("99", new Student(fileMock.get(0)).getGrade());
    }

    @Test
    public void shouldSortById() throws Exception {
        ArrayList<String> fileMock = new ArrayList<>();
        Storage storageMock = Mockito.mock(Storage.class);
        setupStorageMock(fileMock, storageMock);
        String fileName = "large";

        String fileContents = getFileContent(fileName);
        setupInitializer(fileName, storageMock);

        String[] expected = Arrays.stream(fileContents.split("\\n"))
                .map(s -> new Student(s).getId())
                .distinct()
                .sorted()
                .toArray(String[]::new);

        String[] actual = fileMock.stream()
                .map(s -> new Student(s).getId())
                .toArray(String[]::new);

        assertTrue(Arrays.equals(expected,actual));
    }
}
