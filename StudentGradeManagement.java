import java.util.*;

public class StudentGradeManagement {
    // Map to store student names and their grades
    private static Map<String, Double> studentGrades = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display menu
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1: Enter a student name and grade");
            System.out.println("2: Modify a student grade");
            System.out.println("3: Delete a student");
            System.out.println("4: Display grade statistics (min, mean, max, std dev)");
            System.out.println("5: Quit (Enter 'q' or 'Q')");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // Process menu options
            switch (choice) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    modifyStudentGrade(scanner);
                    break;
                case "3":
                    deleteStudent(scanner);
                    break;
                case "4":
                    displayStatistics();
                    break;
                case "5":
                case "q":
                case "Q":
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Option 1: Add a student and grade
    private static void addStudent(Scanner scanner) {
        System.out.print("Enter the student's name: ");
        String name = scanner.nextLine();
        if (studentGrades.containsKey(name)) {
            System.out.println("Student " + name + " already exists. Use option 2 to modify the grade.");
            return;
        }

        System.out.print("Enter the student's grade: ");
        try {
            double grade = Double.parseDouble(scanner.nextLine());
            studentGrades.put(name, grade);
            System.out.println("Student " + name + " added with grade " + grade);
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade. Please enter a numeric value.");
        }
    }

    // Option 2: Modify a student's grade
    private static void modifyStudentGrade(Scanner scanner) {
        System.out.print("Enter the student's name to modify: ");
        String name = scanner.nextLine();
        if (studentGrades.containsKey(name)) {
            System.out.print("Enter the new grade: ");
            try {
                double grade = Double.parseDouble(scanner.nextLine());
                studentGrades.put(name, grade);
                System.out.println("Student " + name + "'s grade updated to " + grade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade. Please enter a numeric value.");
            }
        } else {
            System.out.println("Student " + name + " not found.");
        }
    }

    // Option 3: Delete a student
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the student's name to delete: ");
        String name = scanner.nextLine();
        if (studentGrades.containsKey(name)) {
            studentGrades.remove(name);
            System.out.println("Student " + name + " has been removed.");
        } else {
            System.out.println("Student " + name + " not found.");
        }
    }

    // Option 4: Display statistics
    private static void displayStatistics() {
        if (studentGrades.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        List<Double> grades = new ArrayList<>(studentGrades.values());

        // Calculate statistics
        double min = Collections.min(grades);
        double max = Collections.max(grades);
        double mean = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double stdDev = calculateStandardDeviation(grades, mean);

        // Display statistics
        System.out.println("\n--- Grade Statistics ---");
        System.out.println("Minimum grade: " + min);
        System.out.println("Maximum grade: " + max);
        System.out.println("Mean grade: " + mean);
        System.out.println("Standard deviation: " + stdDev);
    }

    // Helper method: Calculate standard deviation
    private static double calculateStandardDeviation(List<Double> grades, double mean) {
        double sumSquaredDiffs = grades.stream()
                .mapToDouble(grade -> Math.pow(grade - mean, 2))
                .sum();
        return Math.sqrt(sumSquaredDiffs / grades.size());
    }
}

