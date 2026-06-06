# Yahtzee
A fully functional, multiplayer console-based implementation of the classic dice game **Yahtzee**. This project is built entirely in Java using clean Object-Oriented Programming (OOP) design principles.

## 🚀 Features

- **Multiplayer Support**: Play with 1 to 6 players locally in the terminal.
- **Dynamic Turn Logic**: Standard Yahtzee rules allowing up to 3 rolls per turn with the ability to hold/unhold specific dice.
- **Robust Scoring Validation**: Automatically validates all 13 scoring categories (from Ones up to Large Straights and Yahtzee) and prevents players from overwriting previously used slots.
- **Input Error Handling**: Safely handles invalid user choices, string inputs where numbers are expected, and out-of-bounds selections.

---

## 🛠️ Project Architecture & OOP Structure

The game logic is separated into decoupled, dedicated classes to handle distinct responsibilities:

* **`Main.java`**: The application entry point. It sets up the player configurations, initializes the application, and handles resource cleanup.
* **`Game.java`**: Acts as the central controller. It manages game rounds, handles turn sequences, coordinates dice rolling, and manages input flows.
* **`Player.java`**: Represents an individual player, containing their name and maintaining their unique scorecard.
* **`DiceCup.java`**: Manages the array of 5 `Die` objects. Handles conditional rolling depending on which dice the player decides to "hold".
* **`Die.java`**: A standard 6-sided die component equipped with an independent random number generator.
* **`ScoreSheet.java`**: The core logic center for calculating scores. Tracks filled categories, totals points, and uses data-stream filtering to compute complex combinations like straights.

### Class Dependencies

```mermaid
classDiagram
    Main --> Game : Starts
    Game "1" *-- "1..6" Player : Manages
    Game "1" *-- "1" DiceCup : Directs
    Player "1" *-- "1" ScoreSheet : Owns
    DiceCup "1" *-- "5" Die : Combines

