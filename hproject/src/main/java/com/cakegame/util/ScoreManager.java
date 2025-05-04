// ScoreManager.java
package com.cakegame.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manages high scores
 */
public class ScoreManager {
    private static final String SCORES_FILE = "cake_game_scores.dat";
    private static ScoreManager instance;
    
    public static class ScoreEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String playerName;
        private int score;
        private String difficulty;
        private String date;
        
        public ScoreEntry(String playerName, int score, String difficulty, String date) {
            this.playerName = playerName;
            this.score = score;
            this.difficulty = difficulty;
            this.date = date;
        }
        
        public String getPlayerName() { return playerName; }
        public int getScore() { return score; }
        public String getDifficulty() { return difficulty; }
        public String getDate() { return date; }
    }
    
    private List<ScoreEntry> highScores;
    
    private ScoreManager() {
        highScores = new ArrayList<>();
        loadScores();
    }
    
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }
    
    public void addScore(String playerName, int score, String difficulty, String date) {
        highScores.add(new ScoreEntry(playerName, score, difficulty, date));
        sortScores();
        saveScores();
    }
    
    public List<ScoreEntry> getHighScores() {
        return Collections.unmodifiableList(highScores);
    }
    
    private void sortScores() {
        Collections.sort(highScores, new Comparator<ScoreEntry>() {
            @Override
            public int compare(ScoreEntry e1, ScoreEntry e2) {
                return Integer.compare(e2.getScore(), e1.getScore()); // Descending order
            }
        });
        
        // Trim to top 10
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadScores() {
        try {
            File file = new File(SCORES_FILE);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                highScores = (List<ScoreEntry>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (Exception e) {
            System.err.println("Error loading scores: " + e.getMessage());
            highScores = new ArrayList<>();
        }
    }
    
    private void saveScores() {
        try {
            FileOutputStream fos = new FileOutputStream(SCORES_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highScores);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }
}