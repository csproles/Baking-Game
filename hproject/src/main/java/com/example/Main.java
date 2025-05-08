package com.example;

import com.example.util.GameTimer;
import com.example.util.ScoreTracker;

import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Create the game timer (as a countdown timer)
        // Create a countdown timer (true means countdown mode)
        GameTimer gameTimer = new GameTimer(true);
        
        // Initialize with 2 minutes 30 seconds (150 seconds)
        gameTimer.initialize(150);
        
        // Set what happens when timer runs out
        gameTimer.setOnTimeout(() -> {
            System.out.println("Time's up!");
            
            // Get the current score before transitioning
            int finalScore = ScoreTracker.getInstance().getScore();
            System.out.println("Final score when timer expired: " + finalScore);
            
            javafx.application.Platform.runLater(() -> {
                try {
                    // Pass the final score to the GameOverScreen
                    new GameOverScreen(finalScore).start(new Stage());
                    
                    // Close Level1 if it's open
                    if (Level1.level1 != null) Level1.level1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
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
