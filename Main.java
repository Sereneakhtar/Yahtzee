package yahtzee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=================================");
        System.out.println("         YAHTZEE GAME");
        System.out.println("=================================");
        System.out.println("Roll the dice, hold what you want, and score!\n");
        
        System.out.print("Enter number of players (1–6): ");
        
        // Input validation to ensure an integer is actually entered
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid number of players (1-6): ");
        }
        
        int players = scanner.nextInt();
        players = Math.max(1, Math.min(players, 6));
        
        Game game = new Game(players);
        game.play();
        
        // This line shuts down the stream and removes the yellow warning!
        scanner.close(); 
    }
}
