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
    private boolean fPressed;

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private FrontAnimation frontAnimation;
    private BackAnimation backAnimation;
    private RightAnimation rightAnimation;
    private LeftAnimation leftAnimation;

    private MixingMechanism mixingMechanism = new MixingMechanism();
    private BakingMechanism bakingMechanism = new BakingMechanism();
    private DecoratingMechanism decoratingMechanism = new DecoratingMechanism();
    
    private File playerFile = new File("hproject\\src\\main\\sprites\\Chef_Front_Still.png");
    private Image playerImage = new Image(playerFile.toURI().toString());
    private ImageView player = new ImageView(playerImage);

    File pressMixerFile = new File("hproject\\src\\main\\resources\\pressF.png");
    Image pressMixerImage = new Image(pressMixerFile.toURI().toString());
    ImageView pressMixerImageView = new ImageView(pressMixerImage);

    private Stage mixingStage = new Stage();
    private Stage bakingStage = new Stage();
    private Stage decoratingStage = new Stage();

    // @FXML
    // private ImageView player;

    @FXML
    private Scene scene;

    public void makeMoveable(ImageView player, Scene scene){
        this.player = player;
        this.scene = scene;

        pressMixerImageView.setFitWidth(75);
        pressMixerImageView.setFitHeight(75);
        pressMixerImageView.setPreserveRatio(true);
        pressMixerImageView.setTranslateY(-100);

        movementSetup();
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
                if(player.getLayoutY() > 15){

                    Level1.level1Pane.getChildren().remove(pressMixerImageView);
                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        
                        if(!(Constants.CAKE_MIXED)){

                            backAnimation.stopAnimation();
                            pressMixerImageView.setTranslateX(-((Constants.PLAYABLE_PANE_WIDTH/2) - player.getLayoutX()));
                            Level1.level1Pane.getChildren().addAll(pressMixerImageView);
                        }
                    }
                    //Stopping the player from intersecting with the oven
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                            
                        if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED)){
                        
                            backAnimation.stopAnimation();
                            
                            if(player.getLayoutX() > 0){
                                pressMixerImageView.setTranslateX(-(Constants.PLAYABLE_PANE_WIDTH/2) + player.getLayoutX());
                            }
                            else{
                                pressMixerImageView.setTranslateX(Constants.PLAYABLE_PANE_WIDTH/2 - player.getLayoutX());
                            }
                            Level1.level1Pane.getChildren().addAll(pressMixerImageView);

                        }
                    }
                    //Stopping the player from intersecting with the decorator
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        
                        if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        
                            backAnimation.stopAnimation();
                            pressMixerImageView.setTranslateX(-(Constants.PLAYABLE_PANE_WIDTH/2) + player.getLayoutX());
                            Level1.level1Pane.getChildren().addAll(pressMixerImageView);
                        }
                    }
                    else{
                        backAnimation.startAnimation();
                        player.setLayoutY(player.getLayoutY() - Constants.PLAYER_SPEED);
                        System.out.println("Going Up\nY: " + player.getLayoutY());
                    }
                    
                }
                else{System.out.println("CANNOT DO");}
            }

            if(sPressed.get()){
                if(player.getLayoutY() < 350){

                    Level1.level1Pane.getChildren().remove(pressMixerImageView);
<<<<<<< Updated upstream
                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        player.setLayoutY(76);
                    }
                    //Stopping the player from intersecting with the oven
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutY(76);
                    }
                    //Stopping the player from intersecting with the decorator
                    else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        player.setLayoutY(76);
=======
                    if(player.getLayoutY() > 300 && (player.getLayoutX() > -60 && player.getLayoutX() < 130)){
                        player.setLayoutY(290);
>>>>>>> Stashed changes
                    }
                    else{
                        frontAnimation.startAnimation();
                        player.setLayoutY(player.getLayoutY() + Constants.PLAYER_SPEED);
                        System.out.println("Going Up\nY: " + player.getLayoutY());
                    }
                }
                else{System.out.println("CANNOT DO");}
            }

            if(aPressed.get()){

                if(player.getLayoutX() > -20){

                    Level1.level1Pane.getChildren().remove(pressMixerImageView);
                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 60 && ((player.getLayoutX() > 62 && player.getLayoutX() < 208))){
                        System.out.println("CANNOT DO");
                        player.setLayoutX(208);
                    }
                    //Stopping the player from intersecting with the oven
                    else if(player.getLayoutY() < 60 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutX(670);
                    }
                    //Stopping the player from intersecting with the decorator
                    else if(player.getLayoutY() < 60 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        player.setLayoutX(1080);
                    }
                    else if(player.getLayoutY() > 310 && (player.getLayoutX() > -20 && player.getLayoutX() < 130)){
                        player.setLayoutX(130);
                    }
                    else{
                        leftAnimation.startAnimation();
                        player.setLayoutX(player.getLayoutX() - Constants.PLAYER_SPEED);
                        System.out.println("Going Up\nY: " + player.getLayoutY());
                    }

                }
                else{System.out.println("CANNOT DO");}
        }
            else if(dPressed.get()){
                if(player.getLayoutX() < 1200){

                    Level1.level1Pane.getChildren().remove(pressMixerImageView);
                    //Stopping the player from intersecting with the mixer
                    if(player.getLayoutY() < 60 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                        System.out.println("CANNOT DO");
                        player.setLayoutX(62);
                    }
                    //Stopping the player from intersecting with the oven
                    else if(player.getLayoutY() < 60 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                        player.setLayoutX(460);
                    }
                    //Stopping the player from intersecting with the decorator
                    else if(player.getLayoutY() < 60 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                        player.setLayoutX(860);
                    }
                    else if(player.getLayoutY() > 300 && (player.getLayoutX() > 0 && player.getLayoutX() < 130)){
                        player.setLayoutY(290);
                    }
                    else{
                        rightAnimation.startAnimation();
                        player.setLayoutX(player.getLayoutX() + Constants.PLAYER_SPEED);
                        System.out.println("Going Up\nY: " + player.getLayoutY());
                    }

                }
                else{System.out.println("CANNOT DO");}
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

            if(e.getCode() == KeyCode.F){
                fPressed = true;
            }
        });

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(false); 
                
                if(Constants.CAKE_TYPE_VANILLA){
                    if((Constants.CAKE_MIXED || Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_BACK_WITH_PAN_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_BACK_WITH_BOX_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_CHOCOLATE){
                    if((Constants.CAKE_MIXED || Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_BACK_WITH_PAN_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_BACK_WITH_BOX_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_BACK_IMAGEPATH);
                }
                
                System.out.println("Y is: " + player.getLayoutY());
                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.A){
                aPressed.set(false);

                if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_LEFT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_LEFT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_LEFT_WITH_BOX_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_LEFT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_LEFT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_LEFT_WITH_BOX_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_LEFT_IMAGEPATH);
                }
                
                System.out.println("X is: " + player.getLayoutX());
                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.S){
                sPressed.set(false);
                
                if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_FRONT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_FRONT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_FRONT_WITH_BOX_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_FRONT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_FRONT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_FRONT_WITH_BOX_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
                }
                
                System.out.println("Y is: " + player.getLayoutY());
                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.D){
                dPressed.set(false);
                
                if(Constants.CAKE_TYPE_VANILLA){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_UNBAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_BAKED_VANILLA_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_RIGHT_WITH_BOX_IMAGEPATH);
                    }
                }
                else if(Constants.CAKE_TYPE_CHOCOLATE){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_UNBAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        playerFile = new File(Constants.PLAYER_RIGHT_BAKED_CHOCOLATE_IMAGEPATH);
                    }
                    else if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && Constants.CAKE_DECORATED){
                        playerFile = new File(Constants.PLAYER_RIGHT_WITH_BOX_IMAGEPATH);
                    }
                }
                else{
                    playerFile = new File(Constants.PLAYER_RIGHT_IMAGEPATH);
                }
                
                System.out.println("X is: " + player.getLayoutX());
                playerImage = new Image(playerFile.toURI().toString());
                player.setImage(playerImage);
            }

            if(e.getCode() == KeyCode.F){
                if(player.getLayoutY() < 75 && ((player.getLayoutX() > 62 && player.getLayoutX() < 207.5))){
                    if(!(Constants.CAKE_MIXED) && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        mixingMechanism.start(mixingStage);
                    }
                }
                else if(player.getLayoutY() < 75 && (player.getLayoutX() > 460 && player.getLayoutX() < 670)){
                    if(Constants.CAKE_MIXED && !(Constants.CAKE_BAKED) && !(Constants.CAKE_DECORATED)){
                        bakingMechanism.start(bakingStage);
                    }
                }
                else if(player.getLayoutY() < 75 && (player.getLayoutX() > 860 && player.getLayoutX() < 1080)){
                    if(Constants.CAKE_MIXED && Constants.CAKE_BAKED && !(Constants.CAKE_DECORATED)){
                        decoratingMechanism.start(decoratingStage);
                        Constants.CAKE_DECORATED = true;
                    }
                }
                fPressed = false;
                Level1.level1Pane.getChildren().remove(pressMixerImageView);
            }
        });
    }
}
