package com.example;

public class Constants {

    public static boolean CAKE_MIXED = false;
    public static boolean CAKE_BAKED = false;
    public static boolean CAKE_DECORATED = false;
    public static boolean CAKE_TYPE_VANILLA = false;
    public static boolean CAKE_TYPE_CHOCOLATE = false;


    //General Pane Size Constants
    public static double PANE_WIDTH = 1250; //1250, 1100
    public static double PANE_HEIGHT = 625; //625, 500

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
    public static double MAP_BUTTON_XOFFSET = (-(Constants.PANE_WIDTH/2)) + (Constants.MAP_BUTTON_WIDTH/2) + 5;
    //Y Translation
    public static double MAP_BUTTON_YOFFSET = (-(Constants.PANE_HEIGHT/2)) + (Constants.MAP_BUTTON_HEIGHT/2) + 5;

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

    //Normal Player Front
    public static String PLAYER_FRONT_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Front_Still.png";
    public static String PLAYER_FRONT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Front_Step1.png";
    public static String PLAYER_FRONT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Front_Step2.png";

    //Vanilla Player Front
    //UNBAKED
    public static String PLAYER_FRONT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Still_UnbakedVanilla.png";
    public static String PLAYER_FRONT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Step1_UnbakedVanilla.png";
    public static String PLAYER_FRONT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_FRONT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Still_BakedVanilla.png";
    public static String PLAYER_FRONT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Step1_BakedVanilla.png";
    public static String PLAYER_FRONT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Front_Step2_BakedVanilla.png";

    //Chocolate Player Front
    //UNBAKED
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Still_UnbakedChocolate.png";
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Step1_UnbakedChocolate.png";
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Still_BakedChocolate.png";
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Step1_BakedChocolate.png";
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Front_Step2_BakedChocolate.png";

    //Normal Player Back
    public static String PLAYER_BACK_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_Still.png";
    public static String PLAYER_BACK_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_Step1.png";
    public static String PLAYER_BACK_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_Step2.png";

    //Either Cake Back
    public static String PLAYER_BACK_WITH_PAN_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_With_Pan.png";
    public static String PLAYER_BACK_WITH_PAN_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_Step1_With_Pan.png";
    public static String PLAYER_BACK_WITH_PAN_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Back_Step2_With_Pan.png";


    //Normal Player Left
    public static String PLAYER_LEFT_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Left_Still.png";
    public static String PLAYER_LEFT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Left_Step1.png";
    public static String PLAYER_LEFT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Left_Step2.png";

    //Vanilla Player Left
    //UNBAKED
    public static String PLAYER_LEFT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Still_UnbakedVanilla.png";
    public static String PLAYER_LEFT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Step1_UnbakedVanilla.png";
    public static String PLAYER_LEFT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_LEFT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Still_BakedVanilla.png";
    public static String PLAYER_LEFT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Step1_BakedChocolate.png";
    public static String PLAYER_LEFT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Left_Step2_BakedChocolate.png";

    //Chocolate Player Left
    //UNBAKED
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject/src/main/sprites/PlayerWithChocolate/Chef_Left_Still_UnbakedChocolate.png";
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Left_Step1_UnbakedChocolate.png";
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Left_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_IMAGEPATH = "hproject/src/main/sprites/PlayerWithChocolate/Chef_Left_Still_BakedChocolate.png";
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Left_Step1_BakedChocolate.png";
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Left_Step2_BakedChocolate.png";

    //Normal Player Right
    public static String PLAYER_RIGHT_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Right_Still.png";
    public static String PLAYER_RIGHT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Right_Step1.png";
    public static String PLAYER_RIGHT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerMovementWithoutObjects\\Chef_Right_Step2.png";

    //Vanilla Player Right
    //UNBAKED
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Still_UnbakedVanilla.png";
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Step1_UnbakedVanilla.png";
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_RIGHT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Still_BakedVanilla.png";
    public static String PLAYER_RIGHT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Step1_BakedVanilla.png";
    public static String PLAYER_RIGHT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithVanilla\\Chef_Right_Step2_BakedVanilla.png";

    //Chocolate Player Right
    //UNBAKED
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Still_UnbakedChocolate.png";
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Step1_UnbakedChocolate.png";
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Still_BakedChocolate.png";
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Step1_BakedChocolate.png";
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\PlayerWithChocolate\\Chef_Right_Step2_BakedChocolate.png";

    public static double PLAYER_SPEED = 5;

    //Mixer Constants
    public static String MIXER_IMAGEPATH = "hproject\\src\\main\\resources\\mix.png";
    public static double MIXER_LEVEL1_X = PLAYABLE_PANE_WIDTH/2 - 300 - 200;
    public static double MIXER_LEVEL1_Y = -25;

    //Oven Constants
    public static String OVEN_IMAGEPATH = "hproject\\src\\main\\resources\\bake.png";
    public static double OVEN_LEVEL1_X = PLAYABLE_PANE_WIDTH/2 - 100;
    public static double OVEN_LEVEL1_Y = -25;

    //Decoration Station Constants //TODO
    public static String DECORATIONSTATION_IMAGEPATH = "hproject\\src\\main\\resources\\decorate.png";
    public static double DECORATIONSTATION_LEVEL1_X = PLAYABLE_PANE_WIDTH/2 + 300;
    public static double DECORATIONSTATION_LEVEL1_Y = -25;
}
