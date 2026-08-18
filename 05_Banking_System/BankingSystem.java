import java.util.Scanner;

public class BankingSystem {
    static double balance = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== BANKING SYSTEM ===");
            System.out.println("1. Deposit\n2. Withdraw\n3. Balance\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Amount: ");
                double amount = sc.nextDouble();
                if (amount > 0) balance += amount;
            } else if (choice == 2) {
                System.out.print("Amount: ");
                double amount = sc.nextDouble();
                if (amount > 0 && amount <= balance) balance -= amount;
                else System.out.println("Invalid amount or insufficient balance.");
            } else if (choice == 3) {
                System.out.printf("Balance: %.2f%n", balance);
            } else if (choice == 4) {
                break;
            }
        }
    }
}
