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

// /** The class for handling when button 2 is pressed
//  *  Deletes the level selection stage and creates a new one for level 2
//  *  Calls the level2 class
//  */
// class HandleButton2 implements EventHandler<ActionEvent> {
//     @Override
//     public void handle(ActionEvent e) {
//         System.out.println("Button 2 clicked"); 

//         LS.primaryStage.hide();
//         Stage level2Stage = new Stage();
//         Level2 level2 = new Level2();
//         level2.start(level2Stage);
//     }
// }  

// /** The class for when button 3 is pressed
//  *  Deletes the level selection stage and creates a new one for level 3
//  *  Calls the level3 class
//  */
// class HandleButton3 implements EventHandler<ActionEvent> {
//     @Override
//     public void handle(ActionEvent e) {
//         System.out.println("Button 3 clicked"); 

//         LS.primaryStage.hide();
//         Stage level3Stage = new Stage();
//         Level3 level3 = new Level3();
//         level3.start(level3Stage);
//     }
// }  