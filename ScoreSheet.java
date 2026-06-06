package yahtzee;

import java.util.Arrays;

public class ScoreSheet {
    private Integer[] scores = new Integer[13];

    public boolean isUsed(int index) {
        return scores[index] != null;
    }

    public int getTotalScore() {
        int total = 0;
        for (Integer s : scores) {
            if (s != null) total += s;
        }
        return total;
    }

    public void display() {
        String[] names = {
            "Ones","Twos","Threes","Fours","Fives","Sixes",
            "Triple","Quad","Full House",
            "Small Straight","Large Straight",
            "Yahtzee","Chance"
        };

        for (int i = 0; i < names.length; i++) {
            System.out.printf("%2d - %-15s [%s]%n",
                    i + 1,
                    names[i],
                    scores[i] == null ? "--" : scores[i]);
        }
        System.out.println("Total: " + getTotalScore());
    }

    public void score(int index, int[] dice) {
        Arrays.sort(dice);

        int sum = Arrays.stream(dice).sum();
        int[] count = new int[7];
        for (int d : dice) count[d]++;

        int value = 0;

        switch (index) {
            case 0: case 1: case 2: case 3: case 4: case 5:
                int face = index + 1;
                value = count[face] * face;
                break;

            case 6:
                for (int c : count) if (c >= 3) value = sum;
                break;

            case 7:
                for (int c : count) if (c >= 4) value = sum;
                break;

            case 8:
                boolean has3 = false, has2 = false;
                for (int c : count) {
                    if (c == 3) has3 = true;
                    if (c == 2) has2 = true;
                }
                value = (has3 && has2) ? 25 : 0;
                break;

            case 9:
                value = hasStraight(dice, 4) ? 30 : 0;
                break;

            case 10:
                value = hasStraight(dice, 5) ? 40 : 0;
                break;

            case 11:
                for (int c : count) if (c == 5) value = 50;
                break;

            case 12:
                value = sum;
        }

        scores[index] = value;
    }

    private boolean hasStraight(int[] dice, int length) {
        int run = 1;
        for (int i = 1; i < dice.length; i++) {
            if (dice[i] == dice[i - 1] + 1) run++;
            else if (dice[i] != dice[i - 1]) run = 1;
            if (run >= length) return true;
        }
        return false;
    }
}
