package com.example;

import java.io.File;

import com.example.Constants;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FrontAnimation {

    private File playerFile = new File("hproject\\src\\main\\sprites\\Chef_Front_Still.png");
    private Image playerImage = new Image(playerFile.toURI().toString());
    private ImageView player = new ImageView(playerImage);
    int number = 1;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {
        if(number == 1){

            if(Constants.CAKE_TYPE_VANILLA){
                if(!(Constants.CAKE_MIXED) && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_UNBAKED_VANILLA_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_BAKED_VANILLA_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_WITH_BOX_IMAGEPATH);
                }
            }
            else if(Constants.CAKE_TYPE_CHOCOLATE){
                if(!(Constants.CAKE_MIXED) && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_UNBAKED_CHOCOLATE_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_BAKED_CHOCOLATE_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP1_WITH_BOX_IMAGEPATH);
                }
            }
            else{
                playerFile = new File(Constants.PLAYER_FRONT_STEP1_IMAGEPATH);
            }
            
            playerImage = new Image(playerFile.toURI().toString());
            player.setImage(playerImage);
            
            number = 2;
        } else if( number == 2){

            if(Constants.CAKE_TYPE_VANILLA){
                if(!(Constants.CAKE_MIXED) && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_UNBAKED_VANILLA_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_BAKED_VANILLA_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_WITH_BOX_IMAGEPATH);
                }
            }
            else if(Constants.CAKE_TYPE_CHOCOLATE){
                if(!(Constants.CAKE_MIXED) && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_UNBAKED_CHOCOLATE_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_BAKED_CHOCOLATE_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                    playerFile = new File(Constants.PLAYER_FRONT_STEP2_WITH_BOX_IMAGEPATH);
                }
            }
            else{
                playerFile = new File(Constants.PLAYER_FRONT_STEP2_IMAGEPATH);
            }

            
            playerImage = new Image(playerFile.toURI().toString());
            player.setImage(playerImage);
            
            number = 1;
        }
    }));

    public FrontAnimation(ImageView player) {
        this.player = player;
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void startAnimation(){
        timeline.play();
    }

    public void stopAnimation(){
        timeline.stop();
        // playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
        // playerImage = new Image(playerFile.toURI().toString());
        // player.setImage(playerImage);
    }
}