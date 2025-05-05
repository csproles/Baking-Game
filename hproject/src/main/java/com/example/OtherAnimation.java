package com.example;

import java.io.File;

import com.example.Constants;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class OtherAnimation {

    private File playerFile = new File("hproject\\src\\main\\sprites\\Chef_Front_Still.png");
    private Image playerImage = new Image(playerFile.toURI().toString());
    private ImageView player = new ImageView(playerImage);
    int number = 1;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
        if(number == 1){
            // player.setImage(new Image(getFile(Constants.PLAYER_FRONT_IMAGEPATH).getAbsolutePath()));
            // player.setImage(new Image(getClass().getResourceAsStream(Constants.PLAYER_FRONT_IMAGEPATH)));
            playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
            playerImage = new Image(playerFile.toURI().toString());
            player.setImage(playerImage);
            
            number = 2;
        } else if( number == 2){
            // player.setImage(new Image(getFile(Constants.PLAYER_BACK_IMAGEPATH).getAbsolutePath()));
            // player.setImage(new Image(getClass().getResourceAsStream(Constants.PLAYER_FRONT_IMAGEPATH)));
            playerFile = new File(Constants.PLAYER_BACK_IMAGEPATH);
            playerImage = new Image(playerFile.toURI().toString());
            player.setImage(playerImage);
            
            number = 1;
        }
    }));

    public OtherAnimation(ImageView player) {
        this.player = player;
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    // private File getFile(String fileName){
    //     return new File(getClass().getResource(fileName).getPath());
    // }

    public void startAnimation(){
        timeline.play();
    }

    public void stopAnimation(){
        timeline.stop();
    }
}

// import java.io.File;

// import javafx.animation.Animation;
// import javafx.animation.KeyFrame;
// import javafx.animation.Timeline;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.util.Duration;

// public class OtherAnimation {
    
//     private ImageView runner;
//     int number = 1;

//     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
//         if(number == 1){
//             runner.setImage(new Image(getFile(Constants.PLAYER_FRONT_IMAGEPATH).getAbsolutePath()));
//             number = 2;
//         }
//         else if(number == 2){
//             runner.setImage(new Image(getFile(Constants.PLAYER_BACK_IMAGEPATH).getAbsolutePath()));
//             number = 1;
//         }
//     }));

//     public OtherAnimation(ImageView runner){
//         this.runner = runner;
//         timeline.setCycleCount(Animation.INDEFINITE);
//     }

//     private File getFile(String fileName){
//         return new File(getClass().getResource(fileName).getPath());
//     }

//     public void startAnimation(){
//         timeline.play();
//     }

//     public void stopAnimation(){
//         timeline.stop();
//     }
// }
