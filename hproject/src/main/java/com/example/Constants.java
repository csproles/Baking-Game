package com.example;

public class Constants {

    public static boolean HAS_ORDERED = false;
    public static boolean CAKE_MIXED = false;
    public static boolean CAKE_BAKED = false;
    public static boolean CAKE_DECORATED = false;
    public static boolean CAKE_TYPE_VANILLA = false;
    public static boolean CAKE_TYPE_CHOCOLATE = false;


    //General Pane Size Constants
    public static double PANE_WIDTH = 1250; //1250, 1100
    public static double PANE_HEIGHT = 625; //625, 500

    //Level-Selection Pane Color/Style Constants
    private static String LS_PANE_BORDER_HEXCODE = "#677830;";
    private static String LS_PANE_BACKGROUND_HEXCODE = "#B1B371;";
    public static String LS_PANE_STYLE = "-fx-border-color:" + LS_PANE_BORDER_HEXCODE + "-fx-background-color:" + LS_PANE_BACKGROUND_HEXCODE;
    
    //Map Color/Style Constants
    private static String MAP_PANE_BORDER_HEXCODE = "#677830;";
    private static String MAP_PANE_BACKGROUND_HEXCODE = "#89A040;";
    public static String MAP_PANE_STYLE = "-fx-border-color:" + MAP_PANE_BORDER_HEXCODE + "-fx-background-color:" + MAP_PANE_BACKGROUND_HEXCODE;

    //Level-Selection Button Size
    public static double LS_BUTTON_SIZE = PANE_WIDTH/4;

    //Map Button Sizes
    public static double MAP_BUTTON_WIDTH = 150;
    public static double MAP_BUTTON_HEIGHT = 50;

    //Playable Pane Color/Style
    private static String PLAYABLE_PANE_BORDER_HEXCODE = "#677830;";
    private static String PLAYABLE_PANE_BACKGROUND_HEXCODE = "#D8C5A1;";
    public static String PLAYABLE_PANE_STYLE = "-fx-border-color:" + PLAYABLE_PANE_BORDER_HEXCODE + "-fx-background-color:" + PLAYABLE_PANE_BACKGROUND_HEXCODE;

    //Map Button Offsets
    //X Translation
    public static double MAP_BUTTON_XOFFSET = (-(Constants.PANE_WIDTH/2)) + (Constants.MAP_BUTTON_WIDTH/2) - 25;
    //Y Translation
    public static double MAP_BUTTON_YOFFSET = (-(Constants.PANE_HEIGHT/2)) + (Constants.MAP_BUTTON_HEIGHT/2) + 5;

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
    //FRONT
    public static String PLAYER_FRONT_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Still.png";
    public static String PLAYER_FRONT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Step1.png";
    public static String PLAYER_FRONT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Step2.png";
    //BACK
    public static String PLAYER_BACK_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Still.png";
    public static String PLAYER_BACK_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step1.png";
    public static String PLAYER_BACK_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step2.png";
    //LEFT
    public static String PLAYER_LEFT_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Still.png";
    public static String PLAYER_LEFT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Step1.png";
    public static String PLAYER_LEFT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Step2.png";
    //RIGHT
    public static String PLAYER_RIGHT_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Still.png";
    public static String PLAYER_RIGHT_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Step1.png";
    public static String PLAYER_RIGHT_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Step2.png";

    //PANNED BACK
    public static String PLAYER_BACK_WITH_PAN_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_With_Pan.png";
    public static String PLAYER_BACK_STEP1_WITH_PAN_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step1_With_Pan.png";
    public static String PLAYER_BACK_STEP2_WITH_PAN_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step2_With_Pan.png";

    //Vanilla Player
    //FRONT
    //UNBAKED
    public static String PLAYER_FRONT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Front\\Chef_Front_Still_UnbakedVanilla.png";
    public static String PLAYER_FRONT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Front\\Chef_Front_Step1_UnbakedVanilla.png";
    public static String PLAYER_FRONT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Front\\Chef_Front_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_FRONT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Front\\Chef_Front_Still_BakedVanilla.png";
    public static String PLAYER_FRONT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Front\\Chef_Front_Step1_BakedVanilla.png";
    public static String PLAYER_FRONT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Front\\Chef_Front_Step2_BakedVanilla.png";
    //LEFT
    //UNBAKED
    public static String PLAYER_LEFT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Left\\Chef_Left_Still_UnbakedVanilla.png";
    public static String PLAYER_LEFT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Left\\Chef_Left_Step1_UnbakedVanilla.png";
    public static String PLAYER_LEFT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Left\\Chef_Left_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_LEFT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Left\\Chef_Left_Still_BakedVanilla.png";
    public static String PLAYER_LEFT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Left\\Chef_Left_Step1_BakedVanilla.png";
    public static String PLAYER_LEFT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Left\\Chef_Left_Step2_BakedVanilla.png";
    //RIGHT
    //UNBAKED
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Right\\Chef_Right_Still_UnbakedVanilla.png";
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Right\\Chef_Right_Step1_UnbakedVanilla.png";
    public static String PLAYER_RIGHT_UNBAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Unbaked\\Right\\Chef_Right_Step2_UnbakedVanilla.png";
    //BAKED
    public static String PLAYER_RIGHT_BAKED_VANILLA_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Right\\Chef_Right_Still_BakedVanilla.png";
    public static String PLAYER_RIGHT_BAKED_VANILLA_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Right\\Chef_Right_Step1_BakedVanilla.png";
    public static String PLAYER_RIGHT_BAKED_VANILLA_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Vanilla\\Baked\\Right\\Chef_Right_Step2_BakedVanilla.png";

    //Chocolate Player
    //FRONT
    //UNBAKED
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Front\\Chef_Front_Still_UnbakedChocolate.png";
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Front\\Chef_Front_Step1_UnbakedChocolate.png";
    public static String PLAYER_FRONT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Front\\Chef_Front_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Front\\Chef_Front_Still_BakedChocolate.png";
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Front\\Chef_Front_Step1_BakedChocolate.png";
    public static String PLAYER_FRONT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Front\\Chef_Front_Step2_BakedChocolate.png";
    //LEFT
    //UNBAKED
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Left\\Chef_Left_Still_UnbakedChocolate.png";
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Left\\Chef_Left_Step1_UnbakedChocolate.png";
    public static String PLAYER_LEFT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Left\\Chef_Left_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Left\\Chef_Left_Still_BakedChocolate.png";
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Left\\Chef_Left_Step1_BakedChocolate.png";
    public static String PLAYER_LEFT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Left\\Chef_Left_Step2_BakedChocolate.png";
    //RIGHT
    //UNBAKED
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Right\\Chef_Right_Still_UnbakedChocolate.png";
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Right\\Chef_Right_Step1_UnbakedChocolate.png";
    public static String PLAYER_RIGHT_UNBAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Unbaked\\Right\\Chef_Right_Step2_UnbakedChocolate.png";
    //BAKED
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Right\\Chef_Right_Still_BakedChocolate.png";
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Right\\Chef_Right_Step1_BakedChocolate.png";
    public static String PLAYER_RIGHT_BAKED_CHOCOLATE_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chocolate\\Baked\\Right\\Chef_Right_Step2_BakedChocolate.png";

    //Boxed
    //FRONT
    public static String PLAYER_FRONT_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Still_With_Box.png";
    public static String PLAYER_FRONT_WITH_BOX_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Step1_With_Box.png";
    public static String PLAYER_FRONT_WITH_BOX_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Front_Step2_With_Box.png";
    //LEFT
    public static String PLAYER_LEFT_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Still_With_Box.png";
    public static String PLAYER_LEFT_WITH_BOX_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Step1_With_Box.png";
    public static String PLAYER_LEFT_WITH_BOX_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Left_Step2_With_Box.png";
    //RIGHT
    public static String PLAYER_RIGHT_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Still_With_Box.png";
    public static String PLAYER_RIGHT_WITH_BOX_STEP1_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Step1_With_Box.png";
    public static String PLAYER_RIGHT_WITH_BOX_STEP2_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Right_Step2_With_Box.png";
    //BOXED BACK
    public static String PLAYER_BACK_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_With_Box.png";
    public static String PLAYER_BACK_STEP1_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step1_With_Box.png";
    public static String PLAYER_BACK_STEP2_WITH_BOX_IMAGEPATH = "hproject\\src\\main\\sprites\\Player\\Chef_Back_Step2_With_Box.png";
    
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

    public static final String[] CAKE_OPTIONS = {
        "cut_chocolate_chocolate_sprinkles.png",
        "cut_chocolate_chocolate_strawberry.png",
        "cut_chocolate_strawberry_sprinkles.png",
        "cut_chocolate_strawberry_strawberry.png",
        "cut_vanilla_chocolate_sprinkles.png",
        "cut_vanilla_chocolate_strawberry.png",
        "cut_vanilla_strawberry_sprinkles.png",
        "cut_vanilla_strawberry_strawberry.png"
    };

    public static int CURRENT_ORDER_INDEX = 0;
}
