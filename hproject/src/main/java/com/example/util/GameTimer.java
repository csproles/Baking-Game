package com.example.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

/**
 * A reusable game timer class for the cake baking game.
 * Can be used for countdown timers or stopwatches.
 */
public class GameTimer {
    private Timeline timeline;
    private IntegerProperty secondsProperty = new SimpleIntegerProperty(0);
    private boolean isCountdown;
    private Runnable timeoutAction;
    
    /**
     * Creates a new GameTimer
     * 
     * @param isCountdown If true, timer will count down; if false, count up
     */
    public GameTimer(boolean isCountdown) {
        this.isCountdown = isCountdown;
    }
    
    /**
     * Initializes the timer with a specific duration in seconds
     * 
     * @param seconds The duration to set
     */
    public void initialize(int seconds) {
        secondsProperty.set(seconds);
        
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            if (isCountdown) {
                secondsProperty.set(secondsProperty.get() - 1);
                
                // Check if countdown reached zero
                if (secondsProperty.get() <= 0) {
                    stop();
                    if (timeoutAction != null) {
                        timeoutAction.run();
                    }
                }
            } else {
                secondsProperty.set(secondsProperty.get() + 1);
            }
        });
        
        timeline.getKeyFrames().add(keyFrame);
    }
    
    /**
     * Start the timer
     */
    public void start() {
        if (timeline != null) {
            timeline.play();
        }
    }
    
    /**
     * Pause the timer
     */
    public void pause() {
        if (timeline != null) {
            timeline.pause();
        }
    }
    
    /**
     * Stop the timer
     */
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }
    
    /**
     * Reset the timer to its initial value
     * 
     * @param seconds The duration to reset to
     */
    public void reset(int seconds) {
        stop();
        secondsProperty.set(seconds);
    }
    
    /**
     * Set an action to execute when countdown reaches zero
     * 
     * @param action The action to execute
     */
    public void setOnTimeout(Runnable action) {
        this.timeoutAction = action;
    }
    
    /**
     * Get the current time value
     * 
     * @return Current time in seconds
     */
    public int getSeconds() {
        return secondsProperty.get();
    }
    
    /**
     * Get the seconds property for binding to UI elements
     * 
     * @return The seconds IntegerProperty
     */
    public IntegerProperty secondsProperty() {
        return secondsProperty;
    }
    
    /**
     * Format the current time value as MM:SS
     * 
     * @return Formatted time string
     */
    public String getFormattedTime() {
        int minutes = secondsProperty.get() / 60;
        int seconds = secondsProperty.get() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}



