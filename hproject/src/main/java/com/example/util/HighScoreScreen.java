package com.example.util;

import com.example.LS;
import com.example.util.ScoreManager;
import com.example.util.ScoreManager.ScoreEntry;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class HighScoreScreen extends Application {
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("High Scores");
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        
        // Header
        Label headerLabel = new Label("High Scores");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        root.setTop(headerBox);
        
        // High Scores Table
        GridPane scoresGrid = new GridPane();
        scoresGrid.setHgap(20);
        scoresGrid.setVgap(10);
        scoresGrid.setPadding(new Insets(10));
        scoresGrid.setAlignment(Pos.CENTER);
        
        // Table Headers
        Label rankHeader = new Label("Rank");
        Label nameHeader = new Label("Name");
        Label scoreHeader = new Label("Score");
        Label difficultyHeader = new Label("Level");
        Label dateHeader = new Label("Date");
        
        rankHeader.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameHeader.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreHeader.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        difficultyHeader.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dateHeader.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        scoresGrid.add(rankHeader, 0, 0);
        scoresGrid.add(nameHeader, 1, 0);
        scoresGrid.add(scoreHeader, 2, 0);
        scoresGrid.add(difficultyHeader, 3, 0);
        scoresGrid.add(dateHeader, 4, 0);
        
        // Get high scores
        List<ScoreEntry> highScores = ScoreManager.getInstance().getHighScores();
        
        // Add scores to table
        for (int i = 0; i < highScores.size(); i++) {
            ScoreEntry entry = highScores.get(i);
            
            Label rankLabel = new Label("#" + (i + 1));
            Label nameLabel = new Label(entry.getPlayerName());
            Label scoreLabel = new Label(String.valueOf(entry.getScore()));
            Label difficultyLabel = new Label(entry.getDifficulty());
            Label dateLabel = new Label(entry.getDate());
            
            rankLabel.setFont(Font.font("Arial", 14));
            nameLabel.setFont(Font.font("Arial", 14));
            scoreLabel.setFont(Font.font("Arial", 14));
            difficultyLabel.setFont(Font.font("Arial", 14));
            dateLabel.setFont(Font.font("Arial", 14));
            
            scoresGrid.add(rankLabel, 0, i + 1);
            scoresGrid.add(nameLabel, 1, i + 1);
            scoresGrid.add(scoreLabel, 2, i + 1);
            scoresGrid.add(difficultyLabel, 3, i + 1);
            scoresGrid.add(dateLabel, 4, i + 1);
        }
        
        root.setCenter(scoresGrid);
        
        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setOnAction(e -> {
            stage.close();
            Stage menuStage = new Stage();
            new LS().start(menuStage);
        });
        
        VBox bottomBox = new VBox(backButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20, 0, 0, 0));
        root.setBottom(bottomBox);
        
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    // Inside ScoreTracker class
private final IntegerProperty score = new SimpleIntegerProperty(0);

public IntegerProperty scoreProperty() {
    return score;
}

public int getScore() {
    return score.get();
}

public void setScore(int value) {
    score.set(value);
}

}

