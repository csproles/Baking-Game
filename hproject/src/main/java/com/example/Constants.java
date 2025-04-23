package com.example;

import javafx.scene.paint.Color;

public class Constants {

    //General Pane Size Constants
    public static double PANE_WIDTH = 1100; //1250
    public static double PANE_HEIGHT = 500; //625

    //Level-Selection Pane Color/Style Constants
    private static String LS_PANE_BORDER_HEXCODE = "#818D92;";
    private static String LS_PANE_BACKGROUND_HEXCODE = "#818D92;";
    public static String LS_PANE_STYLE = "-fx-border-color:" + LS_PANE_BORDER_HEXCODE + "-fx-background-color:" + LS_PANE_BACKGROUND_HEXCODE;
    
    //Map Color/Style Constants
    private static String MAP_PANE_BORDER_HEXCODE = "e36b52;";
    private static String MAP_PANE_BACKGROUND_HEXCODE = "ababab;";
    public static String MAP_PANE_STYLE = "-fx-border-color:" + MAP_PANE_BORDER_HEXCODE + "-fx-background-color:" + MAP_PANE_BACKGROUND_HEXCODE;

    //Level-Selection Button Size
    public static double LS_BUTTON_SIZE = PANE_WIDTH/4;

    //Map Button Sizes
    public static double MAP_BUTTON_WIDTH = 150;
    public static double MAP_BUTTON_HEIGHT = 50;

    //Level-Selection Button Color/Style
    private static String LS_BUTTON_BORDER_HEXCODE = "f9d5c7;";
    private static String LS_BUTTON_BACKGROUND_HEXCODE = "ad5d4e;";
    public static String LS_BUTTON_STYLE = "-fx-border-color: " + LS_BUTTON_BORDER_HEXCODE + "-fx-background-color:" + LS_BUTTON_BACKGROUND_HEXCODE; 

    //Map Button Color/Style
    //TODO

    //Map Button Offsets
    //X Translation
    public static double MAP_BUTTON_XOFFSET = -(Constants.PANE_WIDTH - (Constants.MAP_BUTTON_WIDTH*4.25));
    //Y Translation
    public static double MAP_BUTTON_YOFFSET = -(Constants.PANE_HEIGHT - (Constants.MAP_BUTTON_HEIGHT*5.75));

    //Buttons Text Size
    public static double LS_BUTTON_TEXT_SIZE = LS_BUTTON_SIZE/4;
    public static double MAP_BUTTON_TEXT_SIZE = MAP_BUTTON_HEIGHT/3;

    //Playable Pane Constants
    public static double PLAYABLE_PANE_HEIGHT = (PANE_HEIGHT - ((MAP_BUTTON_HEIGHT*2)) + 25); 
    public static double PLAYABLE_PANE_YOFFSET = MAP_BUTTON_HEIGHT - 20; 
    public static double PLAYABLE_PANE_WIDTH = PANE_WIDTH - 15;

    //Buttons Text
    public static String MAP1_BUTTON_TEXT = "Level\n   1";
    public static String MAP2_BUTTON_TEXT = "Level\n   2";
    public static String MAP3_BUTTON_TEXT = "Level\n   3";
    public static String LS_BUTTON_TEXT = "Map Selection";
}
