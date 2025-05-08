package com.example;

import java.io.File;

import com.example.Constants;
import com.example.util.GameTimer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Creates A Level Selection Screen For The User To Choose What Level They Want To Play
 */
public class LS extends Application {

    public static Stage primaryStage;
    private static GameTimer gameTimer;

    public void setGameTimer(GameTimer timer) {
        gameTimer = timer;
    }

    public void startLevel1() {
        primaryStage.hide();

        gameTimer = new GameTimer(true);
        gameTimer.initialize(150); // 2:30 minutes

        gameTimer.setOnTimeout(() -> {
            System.out.println("Time's up!");
            javafx.application.Platform.runLater(() -> {
                try {
                    new GameOverScreen().start(new Stage());
                    if (Level1.level1 != null) Level1.level1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        Level1 level1 = new Level1();
        level1.setGameTimer(gameTimer);
        Stage level1Stage = new Stage();
        level1.start(level1Stage);
        gameTimer.start();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        File level1File = new File("hproject\\src\\main\\resources\\level1.png");
        File level2File = new File("hproject\\src\\main\\resources\\level2.png");
        File level3File = new File("hproject\\src\\main\\resources\\level3.png");
        File level2FileCS = new File("hproject\\src\\main\\resources\\comingsoon.png");
        File level3FileCS = new File("hproject\\src\\main\\resources\\comingsoon.png");
        File titleFile = new File("hproject\\src\\main\\resources\\bakinggame.png");

        Image level1Image = new Image(level1File.toURI().toString());
        Image level2Image = new Image(level2File.toURI().toString());
        Image level3Image = new Image(level3File.toURI().toString());
        Image level2ImageCS = new Image(level2FileCS.toURI().toString());
        Image level3ImageCS = new Image(level3FileCS.toURI().toString());
        Image titleImage = new Image(titleFile.toURI().toString());

        ImageView level1ImageView = new ImageView(level1Image);
        ImageView level2ImageView = new ImageView(level2Image);
        ImageView level3ImageView = new ImageView(level3Image);
        ImageView level2ImageViewCS = new ImageView(level2ImageCS);
        ImageView level3ImageViewCS = new ImageView(level3ImageCS);
        ImageView titleImageView = new ImageView(titleImage);

        level1ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level1ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level1ImageView.setPreserveRatio(true);

        level2ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level2ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level2ImageView.setPreserveRatio(true);

        level3ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level3ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level3ImageView.setPreserveRatio(true);

        level2ImageViewCS.setFitWidth(250);
        level2ImageViewCS.setFitHeight(250);
        level2ImageViewCS.setPreserveRatio(true);

        level3ImageViewCS.setFitWidth(250);
        level3ImageViewCS.setFitHeight(250);
        level3ImageViewCS.setPreserveRatio(true);

        titleImageView.setFitWidth(950);
        titleImageView.setFitHeight(550);

        level1ImageView.setTranslateX(-Constants.LS_BUTTON_SIZE - Constants.LS_BUTTON_SIZE / 3);
        level3ImageView.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE / 3);
        level3ImageViewCS.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE / 3);

        level1ImageView.setTranslateY(175);
        level2ImageView.setTranslateY(175);
        level3ImageView.setTranslateY(175);
        level2ImageViewCS.setTranslateY(175);
        level3ImageViewCS.setTranslateY(175);
        titleImageView.setTranslateY(-115);

        level1ImageView.setOnMouseClicked(event -> {
            System.out.println("Button 1 clicked");

            gameTimer = new GameTimer(true);
            gameTimer.initialize(150); // 2:30

            gameTimer.setOnTimeout(() -> {
                System.out.println("Time's up!");
                javafx.application.Platform.runLater(() -> {
                    try {
                        new GameOverScreen().start(new Stage());
                        if (Level1.level1 != null) Level1.level1.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });

            LS.primaryStage.hide();
            Stage level1Stage = new Stage();
            Level1 level1 = new Level1();
            level1.setGameTimer(gameTimer);
            level1.start(level1Stage);
            gameTimer.start();
        });

        StackPane levelSelectionPane = new StackPane();
        levelSelectionPane.getChildren().addAll(
            titleImageView,
            level1ImageView,
            level2ImageView,
            level2ImageViewCS,
            level3ImageView,
            level3ImageViewCS
        );
        levelSelectionPane.setStyle(Constants.LS_PANE_STYLE);

        Scene scene = new Scene(levelSelectionPane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        primaryStage.setTitle("Level Selection");
        primaryStage.setScene(scene);

        System.out.println("Current Order b4: " + Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]);
        primaryStage.show();
    }

    public void run() {
        launch();
    }
}
