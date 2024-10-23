package org.mainApp;

import java.util.Scanner;

public class LoginApp {

    private static Database db = new Database("secure_login.db");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop indefinitely
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit"); // Add an exit option
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                signUp(scanner);
            } else if (choice == 2) {
                login(scanner);
            } else if (choice == 3) {
                System.out.println("Exiting application.");
                break; // Exit the loop
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close(); // Close the scanner
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String passwordHash = Utils.hashPassword(password);
        if (db.registerUser(username, passwordHash)) {
            System.out.println("You have been registered successfully!");
        } else {
            System.out.println("Registration failed. Try again.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = db.loginUser(username);
        if (user != null && user.getPasswordHash().equals(Utils.hashPassword(password))) {
            System.out.println("Login successful!");
            System.out.println("Name: Tirivashe Chitanda");
            System.out.println("Reg ID: H230708Z");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
