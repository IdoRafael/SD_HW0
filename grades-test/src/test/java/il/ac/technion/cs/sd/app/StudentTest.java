package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StudentTest extends SdHw0Test {
    private static Random random = new Random();

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
}
