package at.ac.fhcampuswien.boolegans;

//Diese Klasse ist dazu da den Spielernamen und den jeweiligen Punktestand aufzunehmen und zurückzugeben.
public class Player {
    private static String name;
    private static int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Player.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        Player.score = score;
    }
}
