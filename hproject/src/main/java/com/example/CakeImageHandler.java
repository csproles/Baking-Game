package com.example;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CakeImageHandler {
    
    public static void changeImage(int imageIndex){
        File selectedCakeFile = new File("hproject\\src\\main\\resources\\" + Constants.CAKE_OPTIONS[imageIndex]);
        Image selectedCakeImage = new Image(selectedCakeFile.toURI().toString());
        ImageView selectedCakeImageView = new ImageView(selectedCakeImage);

        File noteFile = new File("hproject\\src\\main\\resources\\note.png");
        Image noteImage = new Image(noteFile.toURI().toString());
        ImageView noteImageView = new ImageView(noteImage);

        selectedCakeImageView.setFitWidth(125);
        selectedCakeImageView.setFitHeight(125);

        noteImageView.setFitWidth(200);
        noteImageView.setFitHeight(175);

        selectedCakeImageView.setTranslateX(350);
        selectedCakeImageView.setTranslateY(-240);

        noteImageView.setTranslateX(350);
        noteImageView.setTranslateY(-245);

        System.out.println("\n\n\n\n ADDING IMAGE \n\n\n\n\n");
        Level1.level1Pane.getChildren().addAll(noteImageView, selectedCakeImageView);
    }
}
