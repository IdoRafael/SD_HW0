package il.ac.technion.cs.sd.app;


public class Student {
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

    public Integer getIntegerId() {
        return Integer.valueOf(id);
    }

    public String toCSVString() {
        return id + "," + grade;
    }
}
