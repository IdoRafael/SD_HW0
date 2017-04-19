package il.ac.technion.cs.sd.app;


public class Student implements Comparable<Student> {
    private String id = "";
    private String grade = "";

    public static int MAX_ID = 999999999;
    public static int MAX_GRADE = 100;

    public Student(String idAndGradeCSV) {
        if (idAndGradeCSV == null) {
            return;
        }
        String[] details = idAndGradeCSV.split(",");
        if (details.length >= 1) {
            this.id = details[0];
        }
        if (details.length >= 2) {
            this.grade = details[1];
        }
    }

    public String getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }

    public String toCSVString() {
        return id + "," + grade;
    }

    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return id.equals(((Student)obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
