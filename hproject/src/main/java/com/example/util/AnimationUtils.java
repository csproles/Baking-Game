package com.example.util;

// Animation utilities for game feedback
public class AnimationUtils {
    public static void playMixingAnimation(javafx.scene.Node target) {
        javafx.animation.RotateTransition rotateTransition = 
            new javafx.animation.RotateTransition(javafx.util.Duration.seconds(1), target);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(3);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }
    
    public static void playBakingAnimation(javafx.scene.Node target) {
        javafx.animation.FadeTransition fadeTransition = 
            new javafx.animation.FadeTransition(javafx.util.Duration.seconds(2), target);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.5);
        fadeTransition.setCycleCount(4);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }
    
    public static void playCompletionAnimation(javafx.scene.Node target) {
        javafx.animation.ScaleTransition scaleTransition = 
            new javafx.animation.ScaleTransition(javafx.util.Duration.seconds(0.5), target);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }
    
    public static void showSuccessIndicator(javafx.scene.layout.Pane container, double x, double y) {
        javafx.scene.control.Label successLabel = new javafx.scene.control.Label("✓");
        successLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: green;");
        successLabel.setTranslateX(x);
        successLabel.setTranslateY(y);
        
        container.getChildren().add(successLabel);
        
        javafx.animation.FadeTransition fadeOut = 
            new javafx.animation.FadeTransition(javafx.util.Duration.seconds(1), successLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> container.getChildren().remove(successLabel));
        fadeOut.play();
    }
    
    public static void showErrorIndicator(javafx.scene.layout.Pane container, double x, double y) {
        javafx.scene.control.Label errorLabel = new javafx.scene.control.Label("✗");
        errorLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: red;");
        errorLabel.setTranslateX(x);
        errorLabel.setTranslateY(y);
        
        container.getChildren().add(errorLabel);
        
        javafx.animation.FadeTransition fadeOut = 
            new javafx.animation.FadeTransition(javafx.util.Duration.seconds(1), errorLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> container.getChildren().remove(errorLabel));
        fadeOut.play();
    }
}
