package il.ac.technion.cs.sd.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.OptionalInt;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void idAndGradeShouldBeEmpty() throws Exception {
        Student student = new Student("");

        assertEquals("", student.getId());
        assertEquals("", student.getGrade());
    }


    @Test
    public void examp() throws Exception {
        Student student = new Student("");

        thrown.expect(Exception.class);
        throw new Exception();
    }



}
