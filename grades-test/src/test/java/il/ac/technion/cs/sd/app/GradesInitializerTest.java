package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GradesInitializerTest extends GradesTest {

    @Test
    public void shouldRemoveDuplicates() throws Exception {
        setupInitializer("GradesInitializerDuplicates");

        assertEquals(1, fileMock.size());
        assertEquals("123", new Student(fileMock.getFirst()).getId());
    }

    @Test
    public void shouldLeaveLastGrade() throws Exception {
        setupInitializer("GradesInitializerDuplicates");

        assertEquals("99", new Student(fileMock.getFirst()).getGrade());
    }

    @Test
    public void shouldSortById() throws Exception {
        String fileName = "GradesApplicationLarge";

        String fileContents = getFileContent(fileName);
        setupInitializer(fileName);

        String[] expected = Arrays.stream(fileContents.split("\\n"))
                .map(s -> new Student(s).getId())
                .distinct()
                .sorted()
                .toArray(String[]::new);

        String[] actual = fileMock
                .stream()
                .map(s -> new Student(s).getId())
                .toArray(String[]::new);

        assertTrue(Arrays.equals(expected,actual));
    }
}
