import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students;
    private Scanner scanner;

    public StudentManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void loadStudentsFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            // skip the first line of the file (with the unit name)
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            // skip the second line of the file (with the header)
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.trim().startsWith("//")) { // to ignore comments in the file
                    String[] parts = line.split(",");
                    if (parts.length >= 5) { // to make sure there are at least 5 fields
                        try {
                            String lastName = parts[0].trim();
                            String firstName = parts[1].trim();
                            String name = lastName + " " + firstName;
                            String id = parts[2].trim();
                            double[] marks = new double[3];
                            for (int i = 0; i < 3; i++) {
                                if (i + 3 < parts.length && !parts[i + 3].trim().isEmpty()) {
                                    marks[i] = Double.parseDouble(parts[i + 3].trim());
                                } else {
                                    marks[i] = 0.0; // if there's missing marks, this will be the default
                                }
                            }
                            students.add(new Student(name, id, marks));
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing numbers in line: " + line);
                        }
                    } else {
                        System.out.println("Incorrect number of parts in line: " + line);
                    }
                }
            }
            fileScanner.close();
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    // print all the student details
    public void printAllStudents() {
        for (Student student : students) {

            // formatting to ensure strings, and numbers are outputted in an organized manner
            System.out.printf("%s, %s, %.2f, %.2f, %.2f, Total: %.2f%n",
                    student.getName(), student.getId(),
                    student.getAssessmentMarks()[0], student.getAssessmentMarks()[1],
                    student.getAssessmentMarks()[2], student.getTotalMark());
        }
    }

    // print students below the threshold given by the user
    public void printStudentsBelowThreshold() {
        System.out.print("Enter the threshold value: ");
        double threshold = scanner.nextDouble();
        for (Student student : students) {
            if (student.getTotalMark() < threshold) {
                System.out.printf("%s, %s, Total: %.2f%n",
                        student.getName(), student.getId(), student.getTotalMark());
            }
        }
    }

    public void printTopAndBottomFiveStudents() {
        // bubble sort to sort students by their total marks
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getTotalMark() < students.get(j + 1).getTotalMark()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        // display the top 5 students
        System.out.println("Top 5 students:");
        for (int i = 0; i < 5 && i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%s, %s, Total: %.2f%n",
                    student.getName(), student.getId(), student.getTotalMark());
        }

        // display the bottom 5 students
        System.out.println("Bottom 5 students:");
        for (int i = students.size() - 1; i >= Math.max(0, students.size() - 5); i--) {
            Student student = students.get(i);
            System.out.printf("%s, %s, Total: %.2f%n",
                    student.getName(), student.getId(), student.getTotalMark());
        }
    }
}