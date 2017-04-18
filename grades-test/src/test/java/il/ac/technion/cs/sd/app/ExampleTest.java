package il.ac.technion.cs.sd.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

  @Rule public Timeout globalTimeout = Timeout.seconds(10);

  private static GradesReader setupAndGetReader(String fileName) throws FileNotFoundException {
    String fileContents =
        new Scanner(new File(ExampleTest.class.getResource(fileName).getFile())).useDelimiter("\\Z").next();
    new GradesInitializer().setup(fileContents);
    return new GradesReader();
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
    public void idoTest() throws Exception {
        String[] lines = {"1,99", "0,300", "0,100", "1,100", "0,200", "1,99"};
        ReversedArrayList<String> reversedLines = new ReversedArrayList<>(lines);

        List l = reversedLines
                .stream()
                .map(x -> new Student(x))
                .distinct()
                .sorted()
                .map(s -> s.toCSVString())
                .collect(Collectors.toList());

        assertEquals(2, l.size());
    }

    @Test
    public void idoTest2() throws Exception {
        Student[] lines = new Student[] {new Student("12,300"), new Student("14,100")};
      int k = Collections.binarySearch(Arrays.asList(lines), new Student("20"));

        int a = 5;
    }
}
