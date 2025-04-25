package com.example;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CharacterImage extends Application{

    public static Pane playerPane = new Pane();
    public static File playerFile;

    // public CharacterImage(Pane playablePane){
    //     //this.playerPane = playablePane;
    // }

    @Override
    public void start(Stage primaryStage){
        // Create a pane to hold the image views
        // Pane playerPane = new Pane();
        playerPane.setPadding(new Insets(5, 5, 5, 5));
        
        playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);

        Image playerImage = new Image(playerFile.toURI().toString());

        ImageView playerImageView = new ImageView(playerImage);

        playerImageView.setFitWidth(100);
        playerImageView.setFitHeight(100);
        playerImageView.setPreserveRatio(true);
    
        ImageView playerImageView2 = new ImageView(playerImage);
        playerImageView2.setFitHeight(100);
        playerImageView2.setFitWidth(100);
        playerImageView2.setLayoutX(250);
        playerImageView2.setLayoutY(250);
        playerPane.getChildren().add(playerImageView2);

        ImageView playerImageView3 = new ImageView(playerImage);
        playerImageView3.setFitHeight(100);
        playerImageView3.setFitWidth(100);
        playerImageView3.setLayoutX(100);
        playerImageView3.setLayoutY(100);
        playerImageView3.setRotate(180);
        playerPane.getChildren().add(playerImageView3);
    
        playerPane.getChildren().addAll(playerImageView);

        // Create a scene and place it in the stage
        Scene scene = new Scene(playerPane, Constants.PLAYABLE_PANE_WIDTH, Constants.PLAYABLE_PANE_HEIGHT   );
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){

                if (event.getCode() == KeyCode.W) {
                    playerImageView.setLayoutY(playerImageView.getLayoutY() - 4);
                    playerFile = new File(Constants.PLAYER_BACK_IMAGEPATH);
                } 
                if (event.getCode() == KeyCode.S) {
                    playerImageView.setLayoutY(playerImageView.getLayoutY() + 4);
                }
                if (event.getCode() == KeyCode.D) {
                    playerImageView.setLayoutX(playerImageView.getLayoutX() + 4);
                } 
                if (event.getCode() == KeyCode.A) {
                    playerImageView.setLayoutX(playerImageView.getLayoutX() - 4);
                }
            }
            });
    }

    public static void main(String[] args){launch();}
}