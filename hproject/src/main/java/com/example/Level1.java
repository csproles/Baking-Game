package com.example;

import java.io.File;

import com.example.Constants;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Level1 extends Application{

    public static Stage level1;
    public static StackPane level1Pane;
    
    @Override
    public void start(Stage level1){
        this.level1 = level1;

        level1Pane = new StackPane();
        Pane playablePane = new Pane();

        level1.setTitle("Level 1");

        Rectangle wall = new Rectangle(Constants.PLAYABLE_PANE_WIDTH, 100);
        wall.setFill(Color.web("#B1B371"));
        wall.setStroke(Color.web("#677830"));

        File playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
        Image playerImage = new Image(playerFile.toURI().toString());
        ImageView playerImageView = new ImageView(playerImage);

        File mixerFile = new File(Constants.MIXER_IMAGEPATH);
        Image mixerImage = new Image(mixerFile.toURI().toString());
        ImageView mixerImageView = new ImageView(mixerImage);

        File ovenFile = new File(Constants.OVEN_IMAGEPATH);
        Image ovenImage = new Image(ovenFile.toURI().toString());
        ImageView ovenImageView = new ImageView(ovenImage);

        File decorationStationFile = new File(Constants.DECORATIONSTATION_IMAGEPATH);
        Image decorationStationImage = new Image(decorationStationFile.toURI().toString());
        ImageView decorationStationImageView = new ImageView(decorationStationImage);
        
        File menuFile = new File("hproject\\src\\main\\resources\\menu.png");
        Image menuImage = new Image(menuFile.toURI().toString());
        ImageView menuImageView = new ImageView(menuImage);

        File orderFile = new File("hproject\\src\\main\\resources\\order.png");
        Image orderImage = new Image(orderFile.toURI().toString());
        ImageView orderImageView = new ImageView(orderImage);

        File pickupFile = new File("hproject\\src\\main\\resources\\pickup.png");
        Image pickupImage = new Image(pickupFile.toURI().toString());
        ImageView pickupImageView = new ImageView(pickupImage);

        File floorFile = new File("");
        Image floorImage = new Image(floorFile.toURI().toString());
        ImageView floorImageView = new ImageView(floorImage);

        playerImageView.setFitWidth(100);
        playerImageView.setFitHeight(100);
        playerImageView.setPreserveRatio(true);

        mixerImageView.setFitWidth(200);
        mixerImageView.setFitHeight(200);
        mixerImageView.setPreserveRatio(true);

        ovenImageView.setFitWidth(200);
        ovenImageView.setFitHeight(200);
        ovenImageView.setPreserveRatio(true);

        decorationStationImageView.setFitWidth(200);
        decorationStationImageView.setFitHeight(200);
        decorationStationImageView.setPreserveRatio(true);

        menuImageView.setFitWidth(100);
        menuImageView.setFitHeight(100);
        menuImageView.setPreserveRatio(true);

        orderImageView.setFitWidth(200);
        orderImageView.setFitHeight(200);
        orderImageView.setPreserveRatio(true);

        pickupImageView.setFitWidth(200);
        pickupImageView.setFitHeight(200);
        pickupImageView.setPreserveRatio(true);

        floorImageView.setFitWidth(1250);
        floorImageView.setFitHeight(750);

        playerImageView.setLayoutX(Constants.PANE_WIDTH/2 - 50);
        playerImageView.setLayoutY(Constants.PANE_HEIGHT/2 + 50);
        
        mixerImageView.setLayoutX(Constants.MIXER_LEVEL1_X);
        mixerImageView.setLayoutY(Constants.MIXER_LEVEL1_Y);
        decorationStationImageView.setLayoutX(Constants.DECORATIONSTATION_LEVEL1_X);
        decorationStationImageView.setLayoutY(Constants.DECORATIONSTATION_LEVEL1_Y);
        ovenImageView.setLayoutX(Constants.OVEN_LEVEL1_X);
        ovenImageView.setLayoutY(Constants.OVEN_LEVEL1_Y);

        menuImageView.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuImageView.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        // wall.setLayoutY(0);

        level1Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);

        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        menuImageView.setOnMouseClicked(event -> {
            System.out.println("LS Button clicked");

            Level1.level1.hide();
            Stage lsStage = new Stage();
            LS ls = new LS();
            ls.start(lsStage);
        });

        playablePane.getChildren().addAll(wall, floorImageView, mixerImageView, ovenImageView, decorationStationImageView, playerImageView, orderImageView, pickupImageView);

        level1Pane.getChildren().addAll(playablePane, menuImageView);//playablePane, menuButton);

        Scene level1Scene = new Scene(level1Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level1.setScene(level1Scene);

        MovementController movementController = new MovementController();
        movementController.makeMoveable(playerImageView, level1Scene);

        // level1ImageView.setOnMouseClicked(event -> {
        //     System.out.println("Button 1 clicked");

        //     LS.primaryStage.hide();
        //     Stage level1Stage = new Stage();
        //     Level1 level1 = new Level1();
        //     level1.start(level1Stage);
        // });

        level1.show();
        level1.centerOnScreen();
    }  
}

