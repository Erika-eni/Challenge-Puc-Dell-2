package Game;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        List<Team> teams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int teamsNumber = 0;
        System.out.println("\nEnter the number of teams (8 to 16, even numbers only):");
        teamsNumber = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < teamsNumber; i++) {
            try {
                System.out.println("Enter the name of team " + (i + 1) + ":");
                String name = scanner.nextLine();
                System.out.println("Enter the war cry of team " + (i + 1) + ":");
                String warCry = scanner.nextLine();
                System.out.println("Enter the foundation year of team " + (i + 1) + ":");
                int yearFoundation = scanner.nextInt();
                scanner.nextLine();
                Team team = new Team(name, warCry, yearFoundation);
                teams.add(team);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}
