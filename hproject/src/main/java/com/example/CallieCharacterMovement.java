package com.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CallieCharacterMovement extends Application{

    private double paneWidth = 100;
    private double paneHeight = 100;

    private Image heroImage;

    private static final String HERO_IMAGE =
            "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";

    private Node hero;

    private boolean wantsGoNorth;
    private boolean wantsGoSouth;
    private boolean wantsGoWest;
    private boolean wantsGoEast;
    private boolean isRunning;

    @Override
    public void start(Stage arg0) throws Exception {
        heroImage = new Image(HERO_IMAGE);
        hero = new ImageView(heroImage);

        Group outside = new Group(hero);
        
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
    
}
