package Game;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private String warCry;
    private int yearFoundation;
    private int points;
    private int blots;
    private int plifs;
    private int advrunghs;
    private int grushts;

    public Team(String name, String warCry, int yearFoundation) {
        this.name = name;
        this.warCry = warCry;
        this.yearFoundation = yearFoundation;
        this.points = 50;
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
        this.grushts = 0;
    }

    public String getName() {return name;}
    public String getWarCry() {return warCry;}
    public int getBlots() {return blots;}
    public int getPoints() {return points;}
    public int getPlifs() {return plifs;}
    public int getAdvrunghs() {return advrunghs;}

    public void doBlot() {
        points += 5;
        blots++;
    }
    public void doPlif() {
        points -= 1;
        plifs++;
    }
    public void doAdvrungh() {
        points -= 10;
        advrunghs++;
    }
    public void grushtPoints() {
        points += 3;
        grushts++;
    }

}
