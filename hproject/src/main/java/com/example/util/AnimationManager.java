package com.example.util;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Manages animations for the cake baking game
 * This class will be used by your teammate working on UI
 */
public class AnimationManager {
    
    /**
     * Create a mixing animation for the mixer
     * 
     * @param mixer The mixer node to animate
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createMixingAnimation(Node mixer, EventHandler<ActionEvent> onComplete) {
        // Create a shaking animation
        RotateTransition rotateRight = new RotateTransition(Duration.millis(150), mixer);
        rotateRight.setByAngle(5);
        
        RotateTransition rotateLeft = new RotateTransition(Duration.millis(150), mixer);
        rotateLeft.setByAngle(-10);
        
        RotateTransition rotateCenter = new RotateTransition(Duration.millis(150), mixer);
        rotateCenter.setByAngle(5);
        
        // Repeat the shake several times
        SequentialTransition shakeSequence = new SequentialTransition(
                rotateRight, rotateLeft, rotateCenter);
        shakeSequence.setCycleCount(4);
        
        // Return the complete animation
        SequentialTransition fullAnimation = new SequentialTransition(shakeSequence);
        
        if (onComplete != null) {
            fullAnimation.setOnFinished(onComplete);
        }
        
        return fullAnimation;
    }
    
    /**
     * Create an animation for pouring batter into a cake tin
     * 
     * @param batter The batter node to animate
     * @param startX The starting X position
     * @param startY The starting Y position
     * @param endX The ending X position
     * @param endY The ending Y position
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createPouringAnimation(Node batter, 
                                                      double startX, double startY,
                                                      double endX, double endY,
                                                      EventHandler<ActionEvent> onComplete) {
        // Set initial position
        batter.setLayoutX(startX);
        batter.setLayoutY(startY);
        
        // Create a path for the batter to follow
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
        
        // Create the path transition
        PathTransition pathTransition = new PathTransition(Duration.seconds(2), path, batter);
        
        // Create a fade in/out for the batter
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), batter);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(1));
        
        // Combine the animations
        SequentialTransition sequence = new SequentialTransition(pathTransition, fadeOut);
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create an animation for baking in the oven
     * 
     * @param oven The oven node to animate
     * @param cake The cake node to animate
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createBakingAnimation(Node oven, Node cake, EventHandler<ActionEvent> onComplete) {
        // Create a glow effect for the oven
        Timeline glowTimeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(oven.opacityProperty(), 0.7)),
            new KeyFrame(Duration.seconds(0.5), new KeyValue(oven.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(1.0), new KeyValue(oven.opacityProperty(), 0.7))
        );
        glowTimeline.setCycleCount(5);
        
        // Create a scaling animation for the cake (to show it rising)
        ScaleTransition scaleCake = new ScaleTransition(Duration.seconds(3), cake);
        scaleCake.setFromX(0.8);
        scaleCake.setFromY(0.8);
        scaleCake.setToX(1.0);
        scaleCake.setToY(1.0);
        
        // Combine the animations with a pause
        SequentialTransition sequence = new SequentialTransition(
            glowTimeline,
            new PauseTransition(Duration.seconds(1)),
            scaleCake
        );
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create an animation for preheating the oven
     * 
     * @param oven The oven node to animate
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createPreheatAnimation(Node oven, EventHandler<ActionEvent> onComplete) {
        // Create a glow effect for the oven
        Timeline glowTimeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(oven.opacityProperty(), 0.7)),
            new KeyFrame(Duration.seconds(0.3), new KeyValue(oven.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(0.6), new KeyValue(oven.opacityProperty(), 0.7))
        );
        glowTimeline.setCycleCount(8);
        
        // Return the animation
        SequentialTransition sequence = new SequentialTransition(glowTimeline);
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create an animation for applying a decoration
     * 
     * @param decoration The decoration node to animate
     * @param cake The cake node being decorated
     * @param onComplete Handler to call when animation completes  
     * @return The created animation
     */
    public SequentialTransition createDecorationAnimation(Node decoration, Node cake, EventHandler<ActionEvent> onComplete) {
        // Move decoration to the cake
        TranslateTransition moveToward = new TranslateTransition(Duration.seconds(1), decoration);
        moveToward.setToX(cake.getLayoutX() - decoration.getLayoutX());
        moveToward.setToY(cake.getLayoutY() - decoration.getLayoutY());
        
        // Apply decoration effect
        FadeTransition applyEffect = new FadeTransition(Duration.millis(300), cake);
        applyEffect.setFromValue(1.0);
        applyEffect.setToValue(0.8);
        applyEffect.setCycleCount(2);
        applyEffect.setAutoReverse(true);
        
        // Move decoration away or hide it
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), decoration);
        fadeOut.setToValue(0);
        
        // Combine animations
        SequentialTransition sequence = new SequentialTransition(
            moveToward, 
            applyEffect,
            fadeOut
        );
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create an animation for boxing the cake
     * 
     * @param cake The cake node to animate
     * @param box The box node to animate
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createBoxingAnimation(Node cake, Node box, EventHandler<ActionEvent> onComplete) {
        // Move cake to box
        TranslateTransition moveCake = new TranslateTransition(Duration.seconds(1.5), cake);
        moveCake.setToX(box.getLayoutX() - cake.getLayoutX());
        moveCake.setToY(box.getLayoutY() - cake.getLayoutY());
        
        // Scaling animation for the box (to show it closing)
        ScaleTransition closeBox = new ScaleTransition(Duration.seconds(1), box);
        closeBox.setFromX(1.0);
        closeBox.setFromY(0.7);
        closeBox.setToX(1.0);
        closeBox.setToY(1.0);
        
        // Fade out cake when it's in the box
        FadeTransition fadeCake = new FadeTransition(Duration.seconds(0.5), cake);
        fadeCake.setFromValue(1.0);
        fadeCake.setToValue(0.0);
        
        // Combine animations
        SequentialTransition sequence = new SequentialTransition(
            moveCake,
            new ParallelTransition(closeBox, fadeCake)
        );
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create an animation for delivering the cake to the customer
     * 
     * @param box The box node to animate
     * @param customer The customer node
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public SequentialTransition createDeliveryAnimation(Node box, Node customer, EventHandler<ActionEvent> onComplete) {
        // Move box to customer
        TranslateTransition moveBox = new TranslateTransition(Duration.seconds(2), box);
        moveBox.setToX(customer.getLayoutX() - box.getLayoutX());
        moveBox.setToY(customer.getLayoutY() - box.getLayoutY());
        
        // Make customer "happy" with a little bounce
        TranslateTransition customerJump = new TranslateTransition(Duration.millis(300), customer);
        customerJump.setByY(-20);
        customerJump.setCycleCount(2);
        customerJump.setAutoReverse(true);
        
        // Fade out box when delivered
        FadeTransition fadeBox = new FadeTransition(Duration.seconds(0.5), box);
        fadeBox.setFromValue(1.0);
        fadeBox.setToValue(0.0);
        
        // Combine animations
        SequentialTransition sequence = new SequentialTransition(
            moveBox,
            new ParallelTransition(customerJump, fadeBox)
        );
        
        if (onComplete != null) {
            sequence.setOnFinished(onComplete);
        }
        
        return sequence;
    }
    
    /**
     * Create a celebration animation for completing the game
     * 
     * @param nodes The nodes to animate
     * @param onComplete Handler to call when animation completes
     * @return The created animation
     */
    public ParallelTransition createCelebrationAnimation(Node[] nodes, EventHandler<ActionEvent> onComplete) {
        ParallelTransition parallel = new ParallelTransition();
        
        for (Node node : nodes) {
            // Create a random animation for each node
            RotateTransition rotate = new RotateTransition(Duration.seconds(1), node);
            rotate.setByAngle(360);
            rotate.setCycleCount(2);
            
            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), node);
            scale.setToX(1.2);
            scale.setToY(1.2);
            scale.setCycleCount(2);
            scale.setAutoReverse(true);
            
            ParallelTransition nodeAnimation = new ParallelTransition(rotate, scale);
            parallel.getChildren().add(nodeAnimation);
        }
        
        if (onComplete != null) {
            parallel.setOnFinished(onComplete);
        }
        
        return parallel;
    }
}

