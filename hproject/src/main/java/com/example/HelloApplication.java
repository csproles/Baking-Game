package com.example;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application{

    public static Pane playerPane = new Pane();
    
    @Override
    public void start(Stage stage) throws IOException{
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view"));
        // Scene scene = new Scene(fxmlLoader.load());
        // scene.getRoot().requestFocus();
        // stage.setTitle("Hello");
        // stage.setScene(scene);
        // stage.show();
        
        File playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);

        Image playerImage = new Image(playerFile.toURI().toString());

        ImageView playerImageView = new ImageView(playerImage);

        playerImageView.setFitWidth(100);
        playerImageView.setFitHeight(100);
        playerImageView.setPreserveRatio(true);
        
        playerPane.getChildren().addAll(playerImageView);

        // Create a scene and place it in the stage
        Scene scene = new Scene(playerPane, Constants.PLAYABLE_PANE_WIDTH, Constants.PLAYABLE_PANE_HEIGHT);

        MovementController movementController = new MovementController();
        movementController.makeMoveable(playerImageView, scene);


        stage.setTitle("ShowImage"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    public static void main(String[] args){launch();}
}
