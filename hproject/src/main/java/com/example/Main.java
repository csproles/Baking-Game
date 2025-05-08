package com.example;

import com.example.util.GameTimer;

public class Main {
    public static void main(String[] args) {
        // Create the game timer (as a countdown timer)
       // Create a countdown timer (true means countdown mode)
GameTimer gameTimer = new GameTimer(true);

// Initialize with 3 minutes (180 seconds)
gameTimer.initialize(150);

// Set what happens when timer runs out
gameTimer.setOnTimeout(() -> {
    System.out.println("Time's up!");
    // Add your game over logic here
});

// Pass the timer to Level1
Level1 level1 = new Level1();
level1.setGameTimer(gameTimer);
        
        // Initialize the level selection screen and pass the timer
        LS levelSelectionScreen = new LS();
       
        
        // Run the level selection screen
        levelSelectionScreen.run();
    }
}
