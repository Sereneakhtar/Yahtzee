package yahtzee;

public class Player {
    private String name;
    private ScoreSheet scoreSheet;

    public Player(String name) {
        this.name = name;
        scoreSheet = new ScoreSheet();
    }

    public String getName() {
        return name;
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
}
