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

        while (true) {
            try {
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
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        while (teams.size() > 1) {
            Phase phase = new Phase(teams);
            while (!phase.matchesEnded()) {
                System.out.println("Current matches:");
                for (int i = 0; i < phase.getMatches().size(); i++) {
                    Match match = phase.getMatches().get(i);
                    if (!match.isFinished()) {
                        System.out.println("[" + (i + 1) + "] " + match.getTeam1().getName() + " vs " + match.getTeam2().getName());
                    } else {
                        System.out.println("[" + (i + 1) + "] " + match.getTeam1().getName() + "  v̶s̶ " + match.getTeam2().getName());
                    }
                }
                System.out.println("-------------------------------------------------");
                int index;
                while (true) {
                    try {
                        System.out.println("Select a match to play:");
                        index = scanner.nextInt();
                        scanner.nextLine();
                        if (index >= 0 && index < phase.getMatches().size() && !phase.getMatches().get(index).isFinished()) {
                            index -= 1;
                            break;
                        } else {
                            System.out.println("Invalid match selection. Try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        scanner.nextLine();
                    }
                }
                Match selectedMatch = phase.getMatches().get(index);
                selectedMatch.startMatch();
            }
            teams = phase.getWinners();

            if (teams.size() == 2) {
                System.out.println("Final match: " + teams.get(0).getName() + " vs " + teams.get(1).getName());
                Match finalMatch = new Match(teams.get(0), teams.get(1));
                finalMatch.startMatch();
                teams.clear();
            }
        }


    }
}
