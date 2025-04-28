package com.example;

public class Constants {

    //General Pane Size Constants
    public static double PANE_WIDTH = 1100; //1250
    public static double PANE_HEIGHT = 500; //625

    //Level-Selection Pane Color/Style Constants
    private static String LS_PANE_BORDER_HEXCODE = "#586A6A;";
    private static String LS_PANE_BACKGROUND_HEXCODE = "#586A6A;";
    public static String LS_PANE_STYLE = "-fx-border-color:" + LS_PANE_BORDER_HEXCODE + "-fx-background-color:" + LS_PANE_BACKGROUND_HEXCODE;
    
    //Map Color/Style Constants
    private static String MAP_PANE_BORDER_HEXCODE = "#586A6A;";
    private static String MAP_PANE_BACKGROUND_HEXCODE = "#586A6A;";
    public static String MAP_PANE_STYLE = "-fx-border-color:" + MAP_PANE_BORDER_HEXCODE + "-fx-background-color:" + MAP_PANE_BACKGROUND_HEXCODE;

    //Level-Selection Button Size
    public static double LS_BUTTON_SIZE = PANE_WIDTH/4;

    //Map Button Sizes
    public static double MAP_BUTTON_WIDTH = 150;
    public static double MAP_BUTTON_HEIGHT = 50;

    //Level-Selection Button Color/Style (on the Level Pages)
    private static String LS_BUTTON_BORDER_HEXCODE = "#818D92;";
    private static String LS_BUTTON_BACKGROUND_HEXCODE = "#B9A394;";
    public static String LS_BUTTON_STYLE = "-fx-border-color: " + LS_BUTTON_BORDER_HEXCODE + "-fx-background-color:" + LS_BUTTON_BACKGROUND_HEXCODE;

    //Playable Pane Color/Style
    private static String PLAYABLE_PANE_BORDER_HEXCODE = "#586A6A;";
    private static String PLAYABLE_PANE_BACKGROUND_HEXCODE = "#818D92;";
    public static String PLAYABLE_PANE_STYLE = "-fx-border-color:" + PLAYABLE_PANE_BORDER_HEXCODE + "-fx-background-color:" + PLAYABLE_PANE_BACKGROUND_HEXCODE;

    //Map Button Color/Style
    //TODO
    private static String MAP_BUTTON_BORDER_HEXCODE = "#818D92;";
    private static String MAP_BUTTON_BACKGROUND_HEXCODE = "#B9A394;";
    public static String MAP_BUTTON_STYLE = "-fx-border-color: " + MAP_BUTTON_BORDER_HEXCODE + "-fx-background-color:" + MAP_BUTTON_BACKGROUND_HEXCODE;

    //Map Button Offsets
    //X Translation
    public static double MAP_BUTTON_XOFFSET = -(Constants.PANE_WIDTH - (Constants.MAP_BUTTON_WIDTH*4.25));
    //Y Translation
    public static double MAP_BUTTON_YOFFSET = -(Constants.PANE_HEIGHT - (Constants.MAP_BUTTON_HEIGHT*5.75));

    //Buttons Text Size
    public static double LS_BUTTON_TEXT_SIZE = LS_BUTTON_SIZE/4;
    public static double MAP_BUTTON_TEXT_SIZE = MAP_BUTTON_HEIGHT/3;

    //Playable Pane Constants
    public static double PLAYABLE_PANE_HEIGHT = (PANE_HEIGHT - ((MAP_BUTTON_HEIGHT*3))); 
    public static double PLAYABLE_PANE_YOFFSET = 67.5; 
    public static double PLAYABLE_PANE_WIDTH = PANE_WIDTH - 15;

    //Buttons Text
    public static String MAP1_BUTTON_TEXT = "Level\n   1";
    public static String MAP2_BUTTON_TEXT = "Level\n   2";
    public static String MAP3_BUTTON_TEXT = "Level\n   3";
    public static String LS_BUTTON_TEXT = "Map Selection";

    //Player Image Constants
    public static String PLAYER_FRONT_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Front_Still.png";
    public static String PLAYER_FRONT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Front_Step1.png";
    public static String PLAYER_FRONT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Front_Step2.png";

    public static String PLAYER_BACK_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Back_Still.png";
    public static String PLAYER_LEFT_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Left_Still.png";
    public static String PLAYER_RIGHT_IMAGEPATH = "hproject\\src\\main\\sprites\\Chef_Right_Still.png";

    public static double PLAYER_SPEED = 1.5;
}
