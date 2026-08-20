import java.util.*;

public class LibraryManagementSystem {
    static ArrayList<String> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book\n2. View Books\n3. Search Book\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Book title: ");
                books.add(sc.nextLine());
                System.out.println("Book added.");
            } else if (choice == 2) {
                for (String book : books) System.out.println("- " + book);
            } else if (choice == 3) {
                System.out.print("Search title: ");
                String title = sc.nextLine();
                System.out.println(books.contains(title) ? "Book found." : "Book not found.");
            } else if (choice == 4) {
                break;
            }
        }
    }
}
