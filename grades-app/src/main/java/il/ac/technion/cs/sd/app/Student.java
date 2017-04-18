package il.ac.technion.cs.sd.app;


public class Student implements Comparable<Student> {
    private String id;
    private String grade;

    public Student(String idAndGradeCSV) {
        String[] details = idAndGradeCSV.split(",");
        this.setId(details[0]);
        this.setGrade(details[1]);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
