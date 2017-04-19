package il.ac.technion.cs.sd.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest {
    private static Random random = new Random();

    private static Student initializeRandomStudent() {
        String id = String.valueOf(random.nextInt(Student.MAX_ID + 1));
        String grade = String.valueOf(random.nextInt(Student.MAX_GRADE + 1));

        return new Student(id + "," + grade);
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullStringInConstructorShouldReturnEmptyIdAndGrade() throws Exception {
        Student student = new Student(null);

        assertEquals("", student.getId());
        assertEquals("", student.getGrade());
    }

    @Test
    public void emptyStringInConstructorShouldReturnEmptyIdAndGrade() throws Exception {
        Student student = new Student("");

        assertEquals("", student.getId());
        assertEquals("", student.getGrade());
    }

    @Test
    public void oneCsvTokenInConstructorShouldLeaveGradeEmpty() throws Exception {
        String id = "300";
        Student student = new Student(id);

        assertEquals(id, student.getId());
        assertEquals("", student.getGrade());
    }

    @Test
    public void validStringInConstructorShouldGiveValidStudent() throws Exception {
        String id = String.valueOf(random.nextInt(Student.MAX_ID + 1));
        String grade = String.valueOf(random.nextInt(Student.MAX_GRADE + 1));
        Student student = new Student(id + "," + grade);

        assertEquals(id, student.getId());
        assertEquals(grade, student.getGrade());
    }

    @Test
    public void conversionToCsvShouldBeValid() throws Exception {
        String id = String.valueOf(random.nextInt(Student.MAX_ID + 1));
        String grade = String.valueOf(random.nextInt(Student.MAX_GRADE + 1));
        String csv = id + "," + grade;
        Student student = new Student(csv);

        assertEquals(csv, student.toCSVString());
    }

    @Test
    public void compareToShouldGiveSameResultAsIdComparison() throws Exception {
        Student student[] = {initializeRandomStudent(), initializeRandomStudent()};

        assertEquals(
                student[0].getId().compareTo(student[1].getId())
                , student[0].compareTo(student[1])
        );
    }

    @Test
    public void equalsShouldGiveSameResultAsIdEquals() throws Exception {
        Student student[] = {initializeRandomStudent(), initializeRandomStudent()};

        assertEquals(
                student[0].getId().equals(student[1].getId())
                , student[0].equals(student[1])
        );
    }

    @Test
    public void hashShouldGiveSameResultAsIdHash() throws Exception {
        Student student = initializeRandomStudent();

        assertEquals(
                student.getId().hashCode()
                , student.hashCode()
        );
    }
}
