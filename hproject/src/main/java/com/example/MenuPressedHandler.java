package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/** The class for handling when the ls button is pressed
 *  Deletes the current stage and creates a new one for ls selection
 *  Calls the level selection class
 */
class MenuButtonPressed implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Menu Button clicked");

        Level1.level1.hide();
        Stage lsStage = new Stage();
        LS ls = new LS();
        ls.start(lsStage);
    }
}  