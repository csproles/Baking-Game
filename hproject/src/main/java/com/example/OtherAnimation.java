package com.example;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class OtherAnimation {
    
    private ImageView runner;
    int number = 1;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
        if(number == 1){
            runner.setImage(new Image(getFile(Constants.PLAYER_FRONT_IMAGEPATH).getAbsolutePath()));
            number = 2;
        }
        else if(number == 2){
            runner.setImage(new Image(getFile(Constants.PLAYER_BACK_IMAGEPATH).getAbsolutePath()));
            number = 1;
        }
    }));

    public OtherAnimation(ImageView runner){
        this.runner = runner;
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private File getFile(String fileName){
        return new File(getClass().getResource(fileName).getPath());
    }

    public void startAnimation(){
        timeline.play();
    }

    public void stopAnimation(){
        timeline.stop();
    }
}
