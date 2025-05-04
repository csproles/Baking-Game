package com.cakegame.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.function.Consumer;

public class GameTimer {
    private int timeRemaining;
    private Timeline timeline;
    private Runnable onTimeUp;
    private Consumer<Integer> onTick;

    public GameTimer(int startTimeInSeconds, Consumer<Integer> onTickCallback, Runnable onTimeUpCallback) {
        this.timeRemaining = startTimeInSeconds;
        this.onTick = onTickCallback;
        this.onTimeUp = onTimeUpCallback;
        setupTimer();
    }

    private void setupTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            if (onTick != null) {
                onTick.accept(timeRemaining);
            }
            if (timeRemaining <= 0) {
                timeline.stop();
                if (onTimeUp != null) {
                    onTimeUp.run();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.playFromStart();
    }

    public void stop() {
        timeline.stop();
    }

    public void pause() {
        timeline.pause();
    }

    public void resume() {
        timeline.play();
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}



