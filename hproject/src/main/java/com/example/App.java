package com.example;

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
 * JavaFX App
 */
public class App extends Application {

    public double paneWidth = 1300; //1250
    public double paneHeight = 650; //625

    public double buttonSize = paneWidth/4;

    @Override
    public void start(Stage primaryStage){
        StackPane pane = new StackPane();

        String button1Text = "Level\n    1";
        Button button1 = new Button(button1Text);
        button1.setPrefSize(buttonSize, buttonSize);
        button1.setTranslateX(-paneWidth/3);
        button1.setStyle("-fx-border-color: e36b90; -fx-background-color: e36b52;");
        button1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , buttonSize/4));

        String button2Text = "Level\n    2";
        Button button2 = new Button(button2Text);
        button2.setPrefSize(buttonSize, buttonSize);
        button2.setTranslateX(0);
        button2.setStyle("-fx-border-color: e36b90; -fx-background-color: e36b52;");
        button2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , buttonSize/4));

        String button3Text = "Level\n    3";
        Button button3 = new Button(button3Text);
        button3.setPrefSize(buttonSize, buttonSize);
        button3.setTranslateX(paneWidth/3);
        button3.setStyle("-fx-border-color: e36b90; -fx-background-color: e36b52;");
        button3.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , buttonSize/4));
        
        HandleButton1 handleButton1 = new HandleButton1();
        button1.setOnAction(handleButton1);
        HandleButton2 handleButton2 = new HandleButton2();
        button2.setOnAction(handleButton2);
        HandleButton3 handleButton3 = new HandleButton3();
        button3.setOnAction(handleButton3);
        
        pane.getChildren().addAll(button1, button2, button3);

        pane.setStyle("-fx-border-color: e36b52; -fx-background-color: accaa1;");
        Scene scene1 = new Scene(pane, paneWidth, paneHeight);
        primaryStage.setTitle("Test Pane");
        primaryStage.setScene(scene1);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

class HandleButton1 implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Button 1 clicked"); 
    }
}  

class HandleButton2 implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
      System.out.println("Button 2 clicked"); 
    }
}  

class HandleButton3 implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
      System.out.println("Button 3 clicked"); 
    }
}  