import java.util.*;

public class StudentManagementSystem {
    static ArrayList<String> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student\n2. View Students\n3. Search Student\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Student name: ");
                students.add(sc.nextLine());
                System.out.println("Student added successfully.");
            } else if (choice == 2) {
                for (int i = 0; i < students.size(); i++)
                    System.out.println((i + 1) + ". " + students.get(i));
            } else if (choice == 3) {
                System.out.print("Enter name to search: ");
                String name = sc.nextLine();
                System.out.println(students.contains(name) ? "Student found." : "Student not found.");
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
