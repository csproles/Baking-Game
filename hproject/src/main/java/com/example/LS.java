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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Creates A Level Selection Screen For The User To Chose What Level They Want To Play
 */
public class LS extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        
        //Assigns the stage variable to the global variable
        this.primaryStage = primaryStage;

        File level1File = new File("hproject\\src\\main\\resources\\level1.png");
        File level2File = new File("hproject\\src\\main\\resources\\level2.png");
        File level3File = new File("hproject\\src\\main\\resources\\level3.png");
        File level2FileCS = new File("hproject\\src\\main\\resources\\othercomingsoon.png");
        File level3FileCS = new File("hproject\\src\\main\\resources\\othercomingsoon.png");
        File titleFile = new File("hproject\\src\\main\\resources\\bakinggame.png");

        Image level1Image = new Image(level1File.toURI().toString());
        Image level2Image = new Image(level2File.toURI().toString());
        Image level3Image = new Image(level3File.toURI().toString());
        Image level2ImageCS = new Image(level2FileCS.toURI().toString());
        Image level3ImageCS = new Image(level3FileCS.toURI().toString());
        Image titleImage = new Image(titleFile.toURI().toString());

        ImageView level1ImageView = new ImageView(level1Image);
        ImageView level2ImageView = new ImageView(level2Image);
        ImageView level3ImageView = new ImageView(level3Image);
        ImageView level2ImageViewCS = new ImageView(level2ImageCS);
        ImageView level3ImageViewCS = new ImageView(level3ImageCS);
        ImageView titleImageView = new ImageView(titleImage);

        level1ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level1ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level1ImageView.setPreserveRatio(true);

        level2ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level2ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level2ImageView.setPreserveRatio(true);

        level3ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level3ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level3ImageView.setPreserveRatio(true);

        level2ImageViewCS.setFitWidth(250);
        level2ImageViewCS.setFitHeight(250);
        level2ImageViewCS.setPreserveRatio(true);

        level3ImageViewCS.setFitWidth(250);
        level3ImageViewCS.setFitHeight(250);
        level3ImageViewCS.setPreserveRatio(true);

        titleImageView.setFitWidth(950);
        titleImageView.setFitHeight(550);

        level1ImageView.setTranslateX(-Constants.LS_BUTTON_SIZE - Constants.LS_BUTTON_SIZE/3);
        level3ImageView.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE/3);
        level3ImageViewCS.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE/3);

        level1ImageView.setTranslateY(175);
        level2ImageView.setTranslateY(175);
        level3ImageView.setTranslateY(175);
        level2ImageViewCS.setTranslateY(175);
        level3ImageViewCS.setTranslateY(175);
        titleImageView.setTranslateY(-115);

        level1ImageView.setOnMouseClicked(event -> {
            System.out.println("Button 1 clicked");

            LS.primaryStage.hide();
            Stage level1Stage = new Stage();
            Level1 level1 = new Level1();
            level1.start(level1Stage);
        });

        //Creates a pane
        StackPane levelSelectionPane = new StackPane();

        //Assigns the buttons to the pane and sets style of the pane
        levelSelectionPane.getChildren().addAll(titleImageView, level1ImageView, level2ImageView, level2ImageViewCS, level3ImageView, level3ImageViewCS);
        levelSelectionPane.setStyle(Constants.LS_PANE_STYLE);
        
        //Creates a scene for level selection
        Scene scene = new Scene(levelSelectionPane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        
        //Names the stage, and assigns the scene to the stage
        primaryStage.setTitle("Level Selection");
        primaryStage.setScene(scene);

        //shows the stage
        primaryStage.show();
    }

    public void run() {
        launch();
    }
}