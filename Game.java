package yahtzee;

import java.util.Scanner;

public class Game {

    private Player[] players;
    private DiceCup cup;
    private Scanner scanner = new Scanner(System.in);

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        cup = new DiceCup();
    }

    public void play() {
        System.out.println("\n=== STARTING YAHTZEE ===");

        for (int round = 1; round <= 13; round++) {
            for (Player player : players) {
                takeTurn(player, round);
            }
        }

        System.out.println("\n=== FINAL SCORES ===");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScoreSheet().getTotalScore());
        }

        // Determine winner
        Player winner = players[0];
        for (Player p : players) {
            if (p.getScoreSheet().getTotalScore() >
                winner.getScoreSheet().getTotalScore()) {
                winner = p;
            }
        }

        System.out.println("\nWinner: " + winner.getName());
    }
    private void takeTurn(Player player, int round) {
    	System.out.println("---------------------------------");
        System.out.println("\n" + player.getName() + " — Round " + round);
        cup.resetHolds();

        for (int roll = 1; roll <= 3; roll++) {
            cup.roll();
            System.out.print("Roll " + roll + ": ");
            cup.displayDice();

            if (roll < 3) {
                System.out.println("Enter die number to HOLD/UNHOLD, or -1 to roll:");

                while (true) {
                    if (!scanner.hasNextInt()) {
                        scanner.next(); // discard bad input
                        System.out.println("Please enter a number (1–5) or -1 to roll.");
                        continue;
                    }

                    int choice = scanner.nextInt();

                    if (choice == -1) {
                        break;
                    }

                    if (choice >= 1 && choice <= 5) {
                        cup.toggleHold(choice - 1);
                        cup.displayDice();
                    } else {
                        System.out.println("Enter 1–5 to toggle a die, or -1 to roll.");
                    }
                }
            }
        }

        ScoreSheet sheet = player.getScoreSheet();
        System.out.println("\nScoring this roll:");
        sheet.display();

        int choice;
        do {
            System.out.print("Choose category (1–13): ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("Please enter a number (1–13): ");
            }
            choice = scanner.nextInt() - 1;
        } while (choice < 0 || choice > 12 || sheet.isUsed(choice));

        sheet.score(choice, cup.getValues());
    }
}
