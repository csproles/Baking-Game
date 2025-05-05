package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/** The class for handling when button 1 is pressed
 *  Deletes the level selection stage and creates a new one for level 1
 *  Calls the level1 class
 */
class HandleButton1 implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Button 1 clicked");

        LS.primaryStage.hide();
        Stage level1Stage = new Stage();
        Level1 level1 = new Level1();
        level1.start(level1Stage);
    }
}  