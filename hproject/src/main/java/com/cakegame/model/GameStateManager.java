package com.cakegame.model;

import com.cakegame.enums.GameState;
import com.cakegame.enums.StationState;
import com.cakegame.enums.GameDifficulty;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
    private GameState currentGameState;
    private StationState currentStation;
    private GameDifficulty difficulty;

    private Cake currentCake;
    private Customer currentCustomer;

    private int score;
    private int timeRemaining;
    private int customersServed;
    private int customersSatisfied;

    private List<Customer> customerQueue;

    // Observer pattern
    public interface GameStateObserver {
        void onGameStateChanged(GameState newState);
        void onStationChanged(StationState newStation);
        void onScoreChanged(int newScore);
        void onTimeChanged(int timeRemaining);
    }

    private final List<GameStateObserver> observers;

    public GameStateManager() {
        this.currentGameState = GameState.MAIN_MENU;
        this.currentStation = StationState.MIXING_STATION;
        this.difficulty = GameDifficulty.MEDIUM;

        this.score = 0;
        this.timeRemaining = 0;
        this.customersServed = 0;
        this.customersSatisfied = 0;

        this.customerQueue = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // Observer management
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameStateObserver observer) {
        observers.remove(observer);
    }

    private void notifyGameStateChanged() {
        for (GameStateObserver observer : observers) {
            observer.onGameStateChanged(currentGameState);
        }
    }

    private void notifyStationChanged() {
        for (GameStateObserver observer : observers) {
            observer.onStationChanged(currentStation);
        }
    }

    private void notifyScoreChanged() {
        for (GameStateObserver observer : observers) {
            observer.onScoreChanged(score);
        }
    }

    private void notifyTimeChanged() {
        for (GameStateObserver observer : observers) {
            observer.onTimeChanged(timeRemaining);
        }
    }

    // Game lifecycle
    public void startNewGame(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        this.currentGameState = GameState.PLAYING;
        this.currentStation = StationState.MIXING_STATION;

        this.score = 0;
        this.timeRemaining = difficulty.getTimeLimit();
        this.customersServed = 0;
        this.customersSatisfied = 0;

        this.currentCake = new Cake();
        this.currentCustomer = new Customer();
        this.customerQueue.clear();

        for (int i = 0; i < 3; i++) {
            customerQueue.add(new Customer());
        }

        notifyGameStateChanged();
        notifyStationChanged();
        notifyScoreChanged();
        notifyTimeChanged();
    }

    public void pauseGame() {
        if (currentGameState == GameState.PLAYING) {
            currentGameState = GameState.PAUSED;
            notifyGameStateChanged();
        }
    }

    public void resumeGame() {
        if (currentGameState == GameState.PAUSED) {
            currentGameState = GameState.PLAYING;
            notifyGameStateChanged();
        }
    }

    public void gameOver() {
        currentGameState = GameState.COMPLETE;
        notifyGameStateChanged();
    }

    public void decrementTimer() {
        if (currentGameState == GameState.PLAYING) {
            timeRemaining--;
            notifyTimeChanged();

            if (timeRemaining <= 0) {
                gameOver();
            }
        }
    }

    // Station transitions
    public void moveToNextStation() {
        switch (currentStation) {
            case MIXING_STATION:
                currentStation = StationState.POURING_STATION;
                break;
            case POURING_STATION:
                currentStation = StationState.BAKING_STATION;
                break;
            case BAKING_STATION:
                currentStation = StationState.DECORATING_STATION;
                break;
            case DECORATING_STATION:
                currentStation = StationState.BOXING_STATION;
                break;
            case BOXING_STATION:
                currentStation = StationState.CUSTOMER_STATION;
                break;
            case CUSTOMER_STATION:
                serveCakeToCustomer();
                resetForNewCake();
                currentStation = StationState.MIXING_STATION;
                break;
            default:
                // Optional: throw or log unexpected state
                break;
        }
        
    private void serveCakeToCustomer() {
        if (currentCake != null && currentCustomer != null && currentCake.isReadyForCustomer()) {
            int satisfaction = currentCustomer.evaluateCake(currentCake);
            int pointsEarned = calculatePoints(satisfaction);

            score += pointsEarned;
            customersServed++;
            if (satisfaction > 70) customersSatisfied++;

            if (!customerQueue.isEmpty()) customerQueue.remove(0);
            customerQueue.add(new Customer());

            notifyScoreChanged();
        }
    }

    private void resetForNewCake() {
        currentCake = new Cake();
        currentCustomer = customerQueue.isEmpty() ? new Customer() : customerQueue.get(0);
    }

    private int calculatePoints(int satisfaction) {
        switch (difficulty) {
            case EASY:
                return satisfaction;
            case MEDIUM:
                return (int) (satisfaction * 1.5);
            case HARD:
                return satisfaction * 2;
            default:
                return satisfaction; // Fallback
        }
        

    // Accessors
    public GameState getCurrentGameState() { return currentGameState; }
    public StationState getCurrentStation() { return currentStation; }
    public GameDifficulty getDifficulty() { return difficulty; }
    public Cake getCurrentCake() { return currentCake; }
    public Customer getCurrentCustomer() { return currentCustomer; }
    public int getScore() { return score; }
    public int getTimeRemaining() { return timeRemaining; }
    public int getCustomersServed() { return customersServed; }
    public int getCustomersSatisfied() { return customersSatisfied; }
    public List<Customer> getCustomerQueue() { return customerQueue; }

    // Optional: Implement if needed
    public void resetToInitialState() {
        // Optional: Implement reset logic
    }

    public void advanceState() {
        // Optional: Implement if state sequencing is more complex
    }

    public List<Node> getAppliedDecorations() {
        // Optional: Return applied decorations if used in UI
        return new ArrayList<>();
    }
}
