package Game;

import java.io.*;
import java.util.List;

public class SaveGame {
    public static void saveGame(String fileName, List<Team> teams, List<Match> matches) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(teams);
            out.writeObject(matches);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving game: " + e.getMessage());
        }
    }
    public static GameData loadGame(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Team> teams = (List<Team>) in.readObject();
            List<Match> matches = (List<Match>) in.readObject();
            System.out.println("Game loaded successfully.");
            return new GameData(teams, matches);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error loading game: " + e.getMessage());
            return null;
        }
    }
    public static void deleteGame(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.delete()) {
            System.out.println("Game deleted successfully.");
        } else {
            System.out.println("⚠️ Error deleting game.");
        }
    }
}
class GameData implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Team> teams;
    private List<Match> matches;

    public GameData(List<Team> teams, List<Match> matches) {
        this.teams = teams;
        this.matches = matches;
    }
    public List<Team> getTeams() {return teams;}
    public List<Match> getMatches() {return matches;}
}
