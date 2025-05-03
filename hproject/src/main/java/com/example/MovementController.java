package com.example;

import java.io.File;

import com.example.Constants;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MovementController {
    
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private OtherAnimation otherAnimation;
    private FrontAnimation frontAnimation;
    private BackAnimation backAnimation;
    private RightAnimation rightAnimation;
    private LeftAnimation leftAnimation;
    
    private File playerFile = new File("hproject\\src\\main\\sprites\\Chef_Front_Still.png");
    private Image playerImage = new Image(playerFile.toURI().toString());
    public ImageView player = new ImageView(playerImage);

    private MixingMechanism mixingMechanism = new MixingMechanism();
    private BakingMechanism bakingMechanism = new BakingMechanism();
    private DecoratingMechanism decoratingMechanism = new DecoratingMechanism();
    private Stage mixerStage = new Stage();
    private Stage bakingStage = new Stage();
    private Stage decoratingStage = new Stage();

    // @FXML
    // private ImageView player;

    @FXML
    private Scene scene;

    public void makeMoveable(ImageView player, Scene scene){
        this.player = player;
        this.scene = scene;

        movementSetup();
        otherAnimation = new OtherAnimation(player);
        frontAnimation = new FrontAnimation(player);
        backAnimation = new BackAnimation(player);
        rightAnimation = new RightAnimation(player);
        leftAnimation = new LeftAnimation(player);

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
                // otherAnimation.startAnimation();
            }
            else{
                timer.stop();
                // otherAnimation.stopAnimation();
                
                leftAnimation.stopAnimation();
                rightAnimation.stopAnimation();
                backAnimation.stopAnimation();
                frontAnimation.stopAnimation();
            }
        }));
    }

    AnimationTimer timer = new AnimationTimer() {
        
        @Override
        public void handle(long timestamp){
            if(wPressed.get()){
                if(player.getLayoutY() > 0){

                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && (player.getLayoutX() > 62 && player.getLayoutX() < 207.5)){
                        System.out.println("CANNOT DO");

                        if(!(Constants.CAKE_MIXED)){
                            Constants.CAKE_MIXED = true;
                        
                            mixingMechanism.start(mixerStage);
                            backAnimation.stopAnimation();
                            stop();
                            
                            player.setLayoutY(90);
                        }
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        
                        if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                            Constants.CAKE_BAKED = true;
                            bakingMechanism.start(bakingStage);
                            backAnimation.stopAnimation();
                            stop();
                            
                            player.setLayoutY(90);
                        }
                        
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        
                        if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                            Constants.CAKE_DECORATED = true;
                            decoratingMechanism.start(decoratingStage);
                            backAnimation.stopAnimation();
                            stop();
                            
                            player.setLayoutY(90);
                        }
                    }
                    else{
                        backAnimation.startAnimation();
                        player.setLayoutY(player.getLayoutY() - Constants.PLAYER_SPEED);
                        System.out.println("Going Back\nY: " + player.getLayoutY());
                    }
                }
                else{
                    System.out.println("CANNOT DO");
                }
            }
            if(sPressed.get()){
                if(player.getLayoutY() < 375){

                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        
                        if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                            // player.setLayoutY(90);
                        }
                    }
                    else{
                        frontAnimation.startAnimation();
                        player.setLayoutY(player.getLayoutY() + Constants.PLAYER_SPEED);
                        System.out.println("Going Forward\nY: " + player.getLayoutY());
                    }
                }
                else{
                    System.out.println("CANNOT DO");
                }
            }

            if(aPressed.get()){
                if(player.getLayoutX() > -20){

                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        
                        if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                            // player.setLayoutY(90);
                        }
                    }
                    else{
                        leftAnimation.startAnimation();
                        player.setLayoutX(player.getLayoutX() - Constants.PLAYER_SPEED);
                        System.out.println("Going Left\nX: " + player.getLayoutX());
                    }
                }
                else{
                    System.out.println("CANNOT DO");
                }
            }
            
            if(dPressed.get()){
                if(player.getLayoutX() < 1200){

                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutY(76);
                    }
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        
                        if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                            // player.setLayoutY(90);
                        }
                    }
                    else{
                        rightAnimation.startAnimation();
                        player.setLayoutX(player.getLayoutX() + Constants.PLAYER_SPEED);
                        System.out.println("Going Right\nX: " + player.getLayoutX());
                    }
                }
                else{
                    System.out.println("CANNOT DO");
                }
            }
        }
    };

    private void movementSetup(){
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A){
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S){
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D){
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(false);    
                
                if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_BACK_WITH_PAN_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                    playerFile = new File(Constants.PLAYER_BACK_WITH_PAN_IMAGEPATH);
                }
                else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                    playerFile = new File(Constants.PLAYER_BACK_WITH_BOX_IMAGEPATH);
                }
                else{
                    playerFile = new File(Constants.PLAYER_BACK_IMAGEPATH);
                }

                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.A){
                aPressed.set(false);
                if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_LEFT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_LEFT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_LEFT_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_LEFT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_LEFT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_LEFT_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_LEFT_IMAGEPATH);
                }

                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.S){
                sPressed.set(false);
                if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_FRONT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_FRONT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_FRONT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_FRONT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
                }

                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.D){
                dPressed.set(false);
                if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_RIGHT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_RIGHT_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED){
                        playerFile = new File(Constants.PLAYER_RIGHT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else{
                        playerFile = new File(Constants.PLAYER_RIGHT_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_RIGHT_IMAGEPATH);
                }

                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }
        });
    }
}
