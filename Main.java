import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // setting up the scanner to get the file name
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        System.out.print("Enter the filename: ");
        String fileName = scanner.nextLine();
        manager.loadStudentsFromFile(fileName);

        // printing the list of options in a simple text-based menu
        while (true) {
            System.out.println("\nSelect option:");
            System.out.println("1. Print all students and their marks");
            System.out.println("2. Print students below a threshold");
            System.out.println("3. Print top 5 and bottom 5 students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        manager.printAllStudents();
                        break;
                    case 2:
                        manager.printStudentsBelowThreshold();
                        break;
                    case 3:
                        manager.printTopAndBottomFiveStudents();
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}