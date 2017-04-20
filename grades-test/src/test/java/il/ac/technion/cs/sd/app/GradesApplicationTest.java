package il.ac.technion.cs.sd.app;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.OptionalInt;
import java.util.Random;

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

/*
    @Test
    public void test() throws Exception {
        String FILENAME = "G:\\Technion\\Courses\\Semester11_Spring_2017\\236700_Software_Design\\HW\\HW0\\base\\grades-test\\src\\test\\resources\\il\\ac\\technion\\cs\\sd\\app\\veryLarge";
        FileWriter fw = new FileWriter(FILENAME);
        BufferedWriter  bw = new BufferedWriter(fw);

        Random random = new Random();

        for (int i = 0; i < 100000; ++i) {
            String id = String.valueOf(i);
            String grade = String.valueOf(random.nextInt(Student.MAX_GRADE + 1));
            String csv = id + "," + grade;
            Student student = new Student(csv);

            bw.write(student.toCSVString() + "\n");
        }
        bw.close();
    }

    @Test
    public void test1() throws Exception {
        GradesReader reader = setupInitializerAndGetReader("veryLarge");
        assertEquals(OptionalInt.of(68), reader.getGrade("99999"));
    }
*/

}
