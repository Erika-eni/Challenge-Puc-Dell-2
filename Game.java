package Game;

import java.util.ArrayList;
import java.util.Comparator;
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
                System.out.println("-------------------------------------------------");
                System.out.println("\nEnter the number of teams (8 to 16, even numbers only):");
                teamsNumber = scanner.nextInt();
                scanner.nextLine();

                if (teamsNumber < 8 || teamsNumber > 16 || teamsNumber % 2 != 0) {
                    System.out.println("⚠️Invalid number of teams.");
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
                            allTeams.add(team);
                            break;
                        } catch (Exception e) {
                            System.out.println("⚠️Invalid input. Please try again.");
                            scanner.nextLine();
                        }
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("⚠️Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        while (teams.size() > 1) {
            Phase phase = new Phase(teams);

            while (!phase.matchesEnded()) {
                System.out.println("-------------------------------------------------");
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
                System.out.println("[S] Save game");
                System.out.println("[L] Load game");
                System.out.println("[D] Delete game");
                System.out.println("Select a match to play:");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("S")) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter the name for the save file:");
                    String fileName = scanner.nextLine();
                    SaveGame.saveGame(fileName + ".dat", teams, phase.getMatches());
                    continue;
                } else if (input.equalsIgnoreCase("L")) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter the name of the save file to load:");
                    String fileName = scanner.nextLine();
                    GameData loadedData = SaveGame.loadGame(fileName + ".dat");
                    if (loadedData != null) {
                        teams = loadedData.getTeams();
                        phase = new Phase(teams);
                        System.out.println("-------------------------------------------------");
                        System.out.println("Loaded matches:");
                        for (int i = 0; i < loadedData.getMatches().size(); i++) {
                            Match match = loadedData.getMatches().get(i);
                            if (match.isFinished()) {
                                System.out.println("[" + (i + 1) + "] " + match.getTeam1().getName() + "  v̶s̶ " + match.getTeam2().getName());
                            } else {
                                System.out.println("[" + (i + 1) + "] " + match.getTeam1().getName() + " vs " + match.getTeam2().getName());
                            }
                        }
                    }
                    continue;
                } else if (input.equalsIgnoreCase("D")) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter the name of the save file to delete:");
                    String fileName = scanner.nextLine();
                    SaveGame.deleteGame(fileName + ".dat");
                    continue;
                }

                int matchIndex;
                try {
                    matchIndex = Integer.parseInt(input) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("⚠️Invalid input. Please try again.");
                    continue;
                }

                if (matchIndex >= 0 && matchIndex < phase.getMatches().size() && !phase.getMatches().get(matchIndex).isFinished()) {
                    Match selectedMatch = phase.getMatches().get(matchIndex);
                    selectedMatch.startMatch();
                } else {
                    System.out.println("⚠️Invalid match selection. Try again.");
                }
            }

            teams = phase.getWinners();

            if (teams.size() == 2) {
                System.out.println("-------------------------------------------------");
                System.out.println("🏁 Final match: " + teams.get(0).getName() + " vs " + teams.get(1).getName());
                Match finalMatch = new Match(teams.get(0), teams.get(1));
                finalMatch.startMatch();
                teams.clear();
            }
        }

        allTeams.sort(Comparator.comparing(Team::getPoints).reversed());
        System.out.println("-------------------------------------------------");
        System.out.println("🏆 Championship Results 🏆");
        System.out.println("\uD83E\uDD47 Gold: " + allTeams.get(0).getName());
        System.out.println("\uD83E\uDD48 Silver: " + allTeams.get(1).getName());
        System.out.println("\uD83E\uDD49 Bronze: " + allTeams.get(2).getName());
        System.out.println("-------------------------------------------------");

        for (Team team : allTeams) {
            System.out.println("--TEAM: " + team.getName());
            System.out.println("War Cry: " + team.getWarCry());
            System.out.println("--Points: " + team.getPoints());
            System.out.println("Blots: " + team.getBlots());
            System.out.println("Plifs: " + team.getPlifs());
            System.out.println("Advrunghs: " + team.getAdvrunghs());
            System.out.println("-------------------------------------------------");
        }
    }
}
