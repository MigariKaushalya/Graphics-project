import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Student name: ");
        String name = sc.nextLine();

        System.out.print("Java marks: ");
        double java = sc.nextDouble();
        System.out.print("Database marks: ");
        double database = sc.nextDouble();
        System.out.print("ICT marks: ");
        double ict = sc.nextDouble();

        double average = (java + database + ict) / 3;
        String grade;

        if (average >= 75) grade = "A";
        else if (average >= 65) grade = "B";
        else if (average >= 55) grade = "C";
        else if (average >= 45) grade = "D";
        else grade = "F";

        System.out.println("\nStudent: " + name);
        System.out.printf("Average: %.2f%n", average);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
