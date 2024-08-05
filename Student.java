public class Student {
    private String name;
    private String id;
    private double[] assessmentMarks;
    private double totalMark;

    // initialises the student details
    // this also calculates and sets the total marks
    public Student(String name, String id, double[] assessmentMarks) {
        this.name = name;
        this.id = id;
        this.assessmentMarks = assessmentMarks;
        this.totalMark = calculateTotalMark();
    }

    private double calculateTotalMark() {
        double total = 0;
        for (double mark : assessmentMarks) {
            total += mark;
        }
        return total;
    }

    // getter methods for accessing the attributes of the student object
    public String getName() { return name; }
    public String getId() { return id; }
    public double[] getAssessmentMarks() { return assessmentMarks; }
    public double getTotalMark() { return totalMark; }
}