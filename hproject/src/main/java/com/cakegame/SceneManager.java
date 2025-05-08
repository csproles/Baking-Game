package com.cakegame;

import java.util.HashMap;
import java.util.Map;

import com.cakegame.enums.StationState;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();
    private Stage primaryStage;
    
    public void registerScene(String id, Scene scene) {
        scenes.put(id, scene);
    }
    
    public void activateScene(String id) {
        if (scenes.containsKey(id)) {
            primaryStage.setScene(scenes.get(id));
        }
    }
    
    public void setupStationTransition(StationState stationType) {
        // Create specific transition animation
        // Activate appropriate scene
        String sceneId = "station_" + stationType.name().toLowerCase();
        activateScene(sceneId);
    }
} 