package il.ac.technion.cs.sd.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;
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
        String[] a = {"0,300", "0,100", "1,100", "0,200"};
        List l = Arrays.stream(a)
                .map(x -> new Student(x))
                .distinct()
                .sorted()
                .map(s -> s.getId() + "," + s.getGrade())
                .collect(Collectors.toList());

        assertEquals(2, l.size());
    }
}
