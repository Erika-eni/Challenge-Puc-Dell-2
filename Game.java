package Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        List<Team> teams = new ArrayList<>();
        List<Team> allTeams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int teamsNumber = 0;
        while (true) {
            try {
                System.out.println("\nEnter the number of teams (8 to 16, even numbers only):");
                teamsNumber = scanner.nextInt();
                scanner.nextLine();

                if (teamsNumber < 8 || teamsNumber > 16 || teamsNumber % 2 != 0) {
                    System.out.println("Invalid number of teams.");
                    continue;
                }

                for (int i = 0; i < teamsNumber; i++) {
                    while (true) {
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
                            allTeams.add(team);  // Adiciona o time à lista de todos os times
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please try again.");
                            scanner.nextLine();
                        }
                    }
                }
                break;
            } catch (InputMismatchException e) {
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

                int matchIndex = -1;
                while (true) {
                    try {
                        System.out.println("Select a match to play:");
                        matchIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (matchIndex >= 0 && matchIndex < phase.getMatches().size() && !phase.getMatches().get(matchIndex).isFinished()) {
                            break;
                        } else {
                            System.out.println("Invalid match selection. Try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        scanner.nextLine();
                    }
                }

                Match selectedMatch = phase.getMatches().get(matchIndex);
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
        
        allTeams.sort(Comparator.comparingInt(Team::getPoints).reversed());

        System.out.println("-----------------------Final standings:-----------------------");
        for (Team team : allTeams) {
            System.out.println("TEAM: " + team.getName());
            System.out.println("War cry: " + team.getWarCry());
            System.out.println("Year of foundation: " + team.getYearFoundation());
            System.out.println("Final points: " + team.getPoints());
            System.out.println("Blots: " + team.getBlots());
            System.out.println("Plifs: " + team.getPlifs());
            System.out.println("Advrunghs: " + team.getAdvrunghs());
            System.out.println("Grushts: " + team.getGrushts());
            System.out.println("-------------------------------------------------");
        }
    }
}
