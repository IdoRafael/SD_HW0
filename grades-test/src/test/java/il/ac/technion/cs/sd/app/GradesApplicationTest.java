package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;

public class GradesApplicationTest extends GradesTest{


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

}
