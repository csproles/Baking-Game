// SoundManager.java
package com.example.util;

import java.applet.AudioClip;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages game sound effects
 */
public class SoundManager {
    private static SoundManager instance;
    private Map<String, AudioClip> soundEffects;
    private boolean isMuted;
    
    private SoundManager() {
        soundEffects = new HashMap<>();
        isMuted = false;
        loadSoundEffects();
    }
    
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }
    
    private void loadSoundEffects() {
        // Load sound effects
        try {
            // Example sound effects
            loadSound("mix", "sounds/mix.wav");
            loadSound("pour", "sounds/pour.wav");
            loadSound("bake", "sounds/bake.wav");
            loadSound("decorate", "sounds/decorate.wav");
            loadSound("complete", "sounds/complete.wav");
            loadSound("customer_happy", "sounds/customer_happy.wav");
            loadSound("customer_unhappy", "sounds/customer_unhappy.wav");
            loadSound("button_click", "sounds/button_click.wav");
        } catch (Exception e) {
            System.err.println("Error loading sound effects: " + e.getMessage());
        }
    }
    
    private void loadSound(String name, String path) {
        try {
            AudioClip clip = new AudioClip(getClass().getResource("/" + path).toExternalForm());
            soundEffects.put(name, clip);
        } catch (Exception e) {
            System.err.println("Could not load sound: " + path);
        }
    }
    
    public void playSound(String name) {
        if (!isMuted && soundEffects.containsKey(name)) {
            soundEffects.get(name).play();
        }
    }
    
    public void stopSound(String name) {
        if (soundEffects.containsKey(name)) {
            soundEffects.get(name).stop();
        }
    }
    
    public void setMuted(boolean muted) {
        this.isMuted = muted;
    }
    
    public boolean isMuted() {
        return isMuted;
    }
}
