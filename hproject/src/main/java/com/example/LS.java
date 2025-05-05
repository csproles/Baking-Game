package com.example;

import com.example.Constants;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        //Creating the level buttons
        Button button1 = new Button(Constants.MAP1_BUTTON_TEXT);
        Button button2 = new Button(Constants.MAP2_BUTTON_TEXT);
        Button button3 = new Button(Constants.MAP3_BUTTON_TEXT);

        //Sets the sizes for the buttons
        button1.setPrefSize(Constants.LS_BUTTON_SIZE, Constants.LS_BUTTON_SIZE);
        button2.setPrefSize(Constants.LS_BUTTON_SIZE, Constants.LS_BUTTON_SIZE);
        button3.setPrefSize(Constants.LS_BUTTON_SIZE, Constants.LS_BUTTON_SIZE);
        
        //Places the button at the right X
        button1.setTranslateX(-Constants.PANE_WIDTH/3);
        button2.setTranslateX(0);
        button3.setTranslateX(Constants.PANE_WIDTH/3);

        //Sets the style/font for the buttons
        button1.setStyle(Constants.LS_BUTTON_STYLE);
        button1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.LS_BUTTON_TEXT_SIZE));
        
        button2.setStyle(Constants.LS_BUTTON_STYLE);
        button2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, Constants.LS_BUTTON_TEXT_SIZE));

        button3.setStyle(Constants.LS_BUTTON_STYLE);
        button3.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.LS_BUTTON_TEXT_SIZE));
        
        //Creates a handler object for each button and when its pressed it calls the class
        HandleButton1 handleButton1 = new HandleButton1();
        HandleButton2 handleButton2 = new HandleButton2();
        HandleButton3 handleButton3 = new HandleButton3();
        
        button1.setOnAction(handleButton1);
        button2.setOnAction(handleButton2);
        button3.setOnAction(handleButton3);

        //Creates a pane
        StackPane levelSelectionPane = new StackPane();

        //Assigns the buttons to the pane and sets style of the pane
        levelSelectionPane.getChildren().addAll(button1, button2, button3);
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