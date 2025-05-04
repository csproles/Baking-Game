// GameDifficulty.java
package com.cakegame.enums;

public enum GameDifficulty {
    EASY(120), // More time in seconds
    MEDIUM(90),
    HARD(60);
    
    private final int timeLimit;
    
    GameDifficulty(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public int getTimeLimit() {
        return timeLimit;
    }
}
