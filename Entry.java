import java.util.Scanner;

public class Entry {
    public static void main(String[] args) {
        Menu menu = new Menu();
        GradeController controller = new GradeController();
        Scanner keyboard = new Scanner(System.in);
        char choice = ' ';

        do {
            menu.displayMenu();
            String input = keyboard.nextLine().trim();
            if (input.length() == 0)
                continue;

            choice = input.toUpperCase().charAt(0);

            switch (choice) {
                case 'A':
                    String entry = controller.getInput("Enter a student name and grade (e.g., Alice 85):", keyboard);
                    if (entry.split(" ").length != 2) {
                        System.out.println("Invalid input format. Use: Name Grade");
                        break;
                    }
                    String name = entry.split(" ")[0];
                    try {
                        int grade = Integer.parseInt(entry.split(" ")[1]);
                        controller.addStudentRecord(name, grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Grade must be a number.");
                    }
                    break;

                case 'D':
                    String nameToDelete = controller.getInput("Enter student name to delete:", keyboard);
                    controller.deleteStudentRecord(nameToDelete);
                    break;

                case 'E': // Evaluate records
                    controller.showStats();
                    break;

                case 'M': // Modify a record
                    String modifyEntry = controller.getInput("Enter a student name and new grade (e.g., Alice 90):", keyboard);
                    if (modifyEntry.split(" ").length != 2) {
                        System.out.println("Invalid input format. Use: Name Grade");
                        break;
                    }
                    String nameToModify = modifyEntry.split(" ")[0];
                    try {
                        int newGrade = Integer.parseInt(modifyEntry.split(" ")[1]);
                        controller.modifyStudentRecord(nameToModify, newGrade);
                    } catch (NumberFormatException e) {
                        System.out.println("Grade must be a number.");
                    }
                    break;

                case 'S':
                    controller.showAllRecords();
                    break;

                case 'Q': case 'q': // Quit the program
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'Q' && choice != 'q'); // Check for both uppercase and lowercase
    }
}

