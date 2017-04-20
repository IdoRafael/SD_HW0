package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GradesInitializerTest extends GradesTest {

    @Test
    public void shouldRemoveDuplicates() throws Exception {
        setupInitializer("duplicates");

        assertEquals(1, fileMock.size());
        assertEquals("123", new Student(fileMock.getFirst()).getId());
    }

    @Test
    public void shouldLeaveLastGrade() throws Exception {
        setupInitializer("duplicates");

        assertEquals("99", new Student(fileMock.getFirst()).getGrade());
    }

    @Test
    public void shouldSortById() throws Exception {
        String fileName = "large";

        String fileContents = getFileContent(fileName);
        setupInitializer(fileName);

        Integer[] expected = Arrays.stream(fileContents.split("\\n"))
                .map(s -> Integer.valueOf(new Student(s).getId()))
                .distinct()
                .sorted()
                .toArray(Integer[]::new);

        Integer[] actual = fileMock
                .stream()
                .map(s -> Integer.valueOf(new Student(s).getId()))
                .toArray(Integer[]::new);

        assertTrue(Arrays.equals(expected,actual));
    }
}
