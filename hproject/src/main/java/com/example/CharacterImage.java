package com.example;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CharacterImage extends Application{

    @Override
    public void start(Stage primaryStage){
        // Create a pane to hold the image views
        Pane pane = new Pane();
        // pane.setPadding(new Insets(5, 5, 5, 5));
        // Image image = new Image("C:\\Users\\calli\\Documents\\GitHub\\HProject\\hproject\\src\\main\\java\\com\\example\\tinyChef.png");
        // ImageView imageView = new ImageView(image);
        // pane.getChildren().add(imageView);
        // imageView.setFitHeight(100);
        // imageView.setFitWidth(100);
    
//    ImageView imageView2 = new ImageView(image);
//    imageView2.setFitHeight(100);
//    imageView2.setFitWidth(100);
//    pane.getChildren().add(imageView2);
//
//    ImageView imageView3 = new ImageView(image);
//    imageView3.setRotate(180);
//    pane.getChildren().add(imageView3);
    
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args){launch();}
}
