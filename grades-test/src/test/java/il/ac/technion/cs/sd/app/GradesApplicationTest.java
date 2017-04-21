package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GradesApplicationTest extends GradesTest{


    @Test
    public void shouldFind123InSmall() throws Exception {
        GradesReader reader = setupInitializerAndGetReader("small");
        assertEquals(OptionalInt.of(100), reader.getGrade("123"));
    }

    @Test
    public void shouldntFind1234InSmall() throws Exception {
        GradesReader reader = setupInitializerAndGetReader("small");
        assertEquals(OptionalInt.empty(), reader.getGrade("1234"));
    }

    @Test
    public void shouldFind123InLarge() throws Exception {
        GradesReader reader = setupInitializerAndGetReader("large");
        assertEquals(OptionalInt.of(100), reader.getGrade("123"));
    }

    @Test
    public void maxStudentShouldFinishInTime() throws Exception {
        GradesReader reader = setupMaxStudentsDatabase();

        assertEquals(OptionalInt.of((MAX_STUDENTS - 1) % 100), reader.getGrade(String.valueOf(MAX_STUDENTS - 1)));
    }
}
