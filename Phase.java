package Game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Phase {
    private List<Match> matches;

    //organiza a partida
    public Phase(List<Team> teams) {
        this.matches = new ArrayList<>();
        Collections.shuffle(teams);
        for (int i = 0; i < teams.size(); i += 2) {
            matches.add(new Match(teams.get(i), teams.get(i + 1)));
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public boolean matchesEnded() {
        for (Match match : matches) {
            if (!match.isFinished()) {
                return false;
            }
        }
        return true;
    }

    public List<Team> getWinners() {
        List<Team> winners = new ArrayList<>();
        for (Match match : matches) {
            Team winner = match.getWinner();
            if (winner != null) {
                winners.add(winner);
            }
        }
        return winners;
    }
}
