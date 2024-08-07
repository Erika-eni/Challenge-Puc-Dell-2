package Game;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Match implements Serializable {
    private Team team1;
    private Team team2;
    private String finished;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.finished = "No";
    }

    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }

    public boolean isFinished() {
        return "Yes".equals(finished);
    }

    public void registerBlot(Team team) {
        if (!isFinished()) {
            team.doBlot();
            seeIfIsEnded();
        }
    }
    public void registerPlif(Team team) {
        if (!isFinished()) {
            team.doPlif();
            seeIfIsEnded();
        }
    }
    public void registerAdvrungh(Team team) {
        if (!isFinished()) {
            team.doAdvrungh();
            seeIfIsEnded();
        }
    }
    private void seeIfIsEnded() {
        if (team1.getPoints() <= 0 || team2.getPoints() <= 0) {
            endMatch();
        }
    }
    public void endMatch() {
        this.finished = "Yes";
        Team winner = getWinner();
        if (winner != null) {
            System.out.println("Match ended. Winner: " + winner.getName());
        } else {
            System.out.println("Match ended (draw). Time to grusht.");
            doGrusht();
        }
    }
    private void doGrusht() {
        System.out.println("-------------------------------------------------");
        System.out.println("Beginning Grusht!");
        Random random = new Random();
        boolean endGrusht = false;
        while (!endGrusht) {
            int size1 = random.nextInt(101);
            int size2 = random.nextInt(101);
            System.out.println("-------------------------------------------------");
            System.out.println("Scream volume from team 1: " + team1.getName() + ": " + size1);
            System.out.println("Scream volume from team 2: " + team2.getName() + ": " + size2);
            if (size1 > size2) {
                team1.grushtPoints();
                System.out.println("Team " + team1.getName() + " won the grusht, more + 3 points.");
                endGrusht = true;
            } else if (size2 > size1) {
                team2.grushtPoints();
                System.out.println("Team " + team2.getName() + " won the grusht, more + 3 points.");
                endGrusht = true;
            } else {
                System.out.println("-------------------------------------------------");
                System.out.println("A tie on Grusht. Restarting...");
                System.out.println("-------------------------------------------------");
            }
        }
    }
    public Team getWinner() {
        if (team1.getPoints() > 0) {
            return team1;
        } else if (team2.getPoints() > 0) {
            return team2;
        } else {
            return null;
        }
    }
    public void startMatch() {
        Scanner scanner = new Scanner(System.in);
        while (!isFinished()) {
            try {
                printMatchStatus();
                int option = scanner.nextInt();
                scanner.nextLine();
                processOption(option);
            } catch (Exception e) {
                System.out.println("⚠️Invalid option, try again.");
                scanner.nextLine();
            }
        }
    }
    private void printMatchStatus() {
        System.out.println("-------------------------------------------------");
        System.out.println("Match: " + team1.getName() + " vs " + team2.getName());
        System.out.println(team1.getName() + " points: " + team1.getPoints());
        System.out.println(team2.getName() + " points: " + team2.getPoints());
        System.out.println("Enter an option:");
        System.out.println("[1] Blot to " + team1.getName());
        System.out.println("[2] Blot to " + team2.getName());
        System.out.println("[3] Plif to " + team1.getName());
        System.out.println("[4] Plif to " + team2.getName());
        System.out.println("[5] Advrungh to " + team1.getName());
        System.out.println("[6] Advrungh to " + team2.getName());
        System.out.println("[7] End match");
        System.out.println("-------------------------------------------------");
    }
    private void processOption(int option) {
        switch (option) {
            case 1 -> registerBlot(team1);
            case 2 -> registerBlot(team2);
            case 3 -> registerPlif(team1);
            case 4 -> registerPlif(team2);
            case 5 -> registerAdvrungh(team1);
            case 6 -> registerAdvrungh(team2);
            case 7 -> endMatch();
            default -> System.out.println("⚠️Invalid option, try again.");
        }
    }
}
