package com.example;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class MovementController {
    
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private OtherAnimation otherAnimation;

    @FXML
    private ImageView sprite;

    @FXML
    private Scene scene;

    public void makeMoveable(ImageView sprite, Scene scene){
        this.sprite = sprite;
        this.scene = scene;

        movementSetup();
        otherAnimation = new OtherAnimation(sprite);

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
                //otherAnimation.startAnimation();
            }
            else{
                timer.stop();
                //otherAnimation.stopAnimation();
            }
        }));
    }

    AnimationTimer timer = new AnimationTimer() {
        
        @Override
        public void handle(long timestamp){
            if(wPressed.get()){
                sprite.setLayoutY(sprite.getLayoutY() - Constants.PLAYER_SPEED);
                System.out.println("Going Back");
            }
            if(sPressed.get()){
                sprite.setLayoutY(sprite.getLayoutY() + Constants.PLAYER_SPEED);
                System.out.println("Going Forward");
            }
            if(aPressed.get()){
                sprite.setLayoutX(sprite.getLayoutX() - Constants.PLAYER_SPEED);
                System.out.println("Going Left");
            }
            if(dPressed.get()){
                sprite.setLayoutX(sprite.getLayoutX() + Constants.PLAYER_SPEED);
                System.out.println("Going Right");
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
            }

            if(e.getCode() == KeyCode.A){
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S){
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D){
                dPressed.set(false);
            }
        });
    }
}
