import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class GradeController {
    private final Map<String, Integer> studentRecords = new HashMap<>();


    public void addStudentRecord(String name, int grade) {
        if (studentRecords.containsKey(name)) {
            System.out.println("Student already exists. Use modify to update the grade.");
        } else {
            studentRecords.put(name, grade);
            System.out.println("Added: " + name + " with grade: " + grade);
        }
    }


    public boolean deleteStudentRecord(String name) {
        if (studentRecords.remove(name) != null) {
            System.out.println("Deleted: " + name);
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }


    public boolean modifyStudentRecord(String name, int newGrade) {
        if (studentRecords.containsKey(name)) {
            studentRecords.put(name, newGrade);
            System.out.println("Updated: " + name + " with new grade: " + newGrade);
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }


    public void showAllRecords() {
        if (studentRecords.isEmpty()) {
            System.out.println("No records to display.");
        } else {
            System.out.println("--- Student Records ---");
            studentRecords.forEach((name, grade) -> System.out.println(name + ": " + grade));
        }
    }


    public void showStats() {
        if (studentRecords.isEmpty()) {
            System.out.println("No records to evaluate.");
            return;
        }

        Collection<Integer> grades = studentRecords.values();
        int min = Collections.min(grades);
        int max = Collections.max(grades);
        double avg = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        System.out.println("--- Grade Statistics ---");
        System.out.printf("Minimum Grade: %d\n", min);
        System.out.printf("Maximum Grade: %d\n", max);
        System.out.printf("Average Grade: %.2f\n", avg);
    }


    public String getInput(String prompt, Scanner keyboard) {
        System.out.println(prompt);
        return keyboard.nextLine();
    }
}
