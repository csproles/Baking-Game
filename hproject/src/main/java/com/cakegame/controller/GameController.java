package com.cakegame.controller;

import com.cakegame.MainSceneController;
import com.cakegame.enums.*;
import com.cakegame.model.*;
import com.cakegame.util.GameConfig;
import com.cakegame.util.GameTimer;

import java.util.function.Consumer;

public class GameController {
    private CakeMix currentMix;
    private Cake currentCake;
    private Customer currentCustomer;
    private GameState currentStage;
    private GameTimer timer;
    private int score;

    private Consumer<GameState> onStageChange;
    private Consumer<Integer> onScoreChange;

    public GameController(Consumer<GameState> onStageChange, Consumer<Integer> onScoreChange) {
        this.onStageChange = onStageChange;
        this.onScoreChange = onScoreChange;
        resetGame();
    }

    public void resetGame() {
        currentMix = new CakeMix();
        currentCake = null;
        currentCustomer = new Customer(); // You can customize customer generation
        score = 0;
        changeState(GameState.MIXING);

        if (onScoreChange != null) {
            onScoreChange.accept(score);
        }
    }

    public void changeState(GameState newStage) {
        if (timer != null) {
            timer.stop();
        }

        currentStage = newStage;

        if (onStageChange != null) {
            onStageChange.accept(currentStage);
        }

        switch (newStage) {
            case MIXING:
                break;

            case BAKING:
                timer = new GameTimer(GameConfig.BAKING_TIME,
                        timeLeft -> {}, // Could update UI with timeLeft
                        () -> {
                            if (currentCake != null) {
                                currentCake.boxCake();
                                changeState(GameState.DECORATING);
                            } else {
                                System.err.println("Error: No cake to bake.");
                                resetGame(); // fallback
                            }
                        });
                timer.start();
                break;

            case DECORATING:
                break;

            case PACKAGING:
                break;

            case COMPLETE:
                calculateFinalScore();
                break;
        }
    }

    private void calculateFinalScore() {
        if (currentCake == null) return;

        score += GameConfig.POINTS_BASE_CAKE;
        score += GameConfig.POINTS_DECORATION;
        score += currentCake.getToppings().size() * GameConfig.POINTS_TOPPING;

        if (currentCake.hasDrip()) {
            score += GameConfig.POINTS_DECORATION;
        }

        if (onScoreChange != null) {
            onScoreChange.accept(score);
        }
    }

    // Mixing Stage
    public void addIngredient(String ingredientName) {
        if (currentStage == GameState.MIXING) {
            Ingredient ingredient = new Ingredient(ingredientName, ingredientName, null);
            currentMix.addIngredient(ingredient);
        }
    }

    public void setFlavor(CakeFlavor flavor) {
        if (currentStage == GameState.MIXING) {
            currentMix.setFlavor(flavor);
        }
    }

    public void mixIngredients() {
        if (currentStage == GameState.MIXING &&
            currentMix.hasRequiredIngredients() &&
            currentMix.getFlavor() != null) {

            currentMix.mix();
            currentCake = new Cake();
            changeState(GameState.BAKING);
        }
    }

    // Baking Stage
    public void checkBakingStatus() {
        if (currentStage == GameState.BAKING && currentCake != null && currentCake.isBaked()) {
            changeState(GameState.DECORATING);
        }
    }

    public void finishBaking() {
        if (currentStage == GameState.BAKING && currentCake != null) {
            currentCake.boxCake();
            changeState(GameState.DECORATING);
        }
    }

    // Decorating Stage
    public void applyIcing(IcingType icingType) {
        if (currentStage == GameState.DECORATING && currentCake != null) {
            currentCake.setIcingType(icingType);
        }
    }

    public void applyBorder(BorderStyle borderStyle) {
        if (currentStage == GameState.DECORATING && currentCake != null) {
            currentCake.setBorderStyle(borderStyle);
        }
    }

    public void addTopping(ToppingType topping) {
        if (currentStage == GameState.DECORATING && currentCake != null) {
            currentCake.addTopping(topping);
        }
    }

    public void toggleDrip() {
        if (currentStage == GameState.DECORATING && currentCake != null) {
            currentCake.setHasDrip(!currentCake.hasDrip());
        }
    }

    public void finishDecorating() {
        if (currentStage == GameState.DECORATING && currentCake.isDecorated()) {
            changeState(GameState.PACKAGING);
        }
    }

    // Packaging Stage
    public void boxCake() {
        if (currentStage == GameState.PACKAGING && currentCake != null) {
            currentCake.boxCake();
            changeState(GameState.COMPLETE);
        }
    }

    // Final customer scoring
    public void addCustomerScore(int satisfactionScore) {
        score += satisfactionScore;
        if (onScoreChange != null) {
            onScoreChange.accept(score);
        }
    }

    public void generateNewCustomer() {
        currentCustomer = new Customer(); // Replace with real generation logic if needed
    }

    // Getters
    public GameState getCurrentStage() {
        return currentStage;
    }

    public CakeMix getCurrentMix() {
        return currentMix;
    }

    public Cake getCurrentCake() {
        return currentCake;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public int getScore() {
        return score;
    }

    // Optional / Advanced
    public void addObserver(MainSceneController mainSceneController) {
        // Optional future implementation
    }

    public void startNewGame(GameDifficulty difficulty) {
        resetGame();
    }

    public void pauseGame() {
        if (timer != null) timer.pause();
    }

    public void exitGame() {
        if (timer != null) timer.stop();
        // Additional cleanup if needed
    }

    public GameStateManager getGameState() {
        return new GameStateManager(); // Stub implementation
    }

    public Object getCakeFlavor() {
        return currentMix != null ? currentMix.getFlavor() : null;
    }

    public RibbonColor getRibbonColor() {
        return currentCake != null ? currentCake.getRibbonColor() : null;
    }

    public boolean hasCard() {
        return currentCake != null && currentCake.hasCard();
    }

    public void setRibbonColor(RibbonColor ribbonColor) {
        if (currentCake != null) {
            currentCake.setRibbonColor(ribbonColor);
        }
    }

    public void addCard() {
        if (currentCake != null) {
            currentCake.setHasCard(true);
        }
    }

    public void setBoxingComplete(boolean isBoxed, RibbonColor ribbonColor, boolean hasCard) {
        if (currentCake != null) {
            currentCake.setBoxed(isBoxed);
            currentCake.setRibbonColor(ribbonColor);
            currentCake.setHasCard(hasCard);
        }
    }

    public void setIcingType(IcingType icing) {
        applyIcing(icing);
    }

    public void setBorderStyle(BorderStyle tip) {
        applyBorder(tip);
    }

    public void addToppingType(String toppingName) {
        ToppingType topping = ToppingType.valueOf(toppingName.toUpperCase());
        addTopping(topping);
    }

    public void setDecoratedCake(Cake decoratedCake) {
        this.currentCake = decoratedCake;
    }

    public Cake getDecoratedCake() {
        return currentCake;
    }

    public void moveToNextStation() {
        switch (currentStage) {
            case MIXING:
                changeState(GameState.BAKING);
                break;
            case BAKING:
                changeState(GameState.DECORATING);
                break;
            case DECORATING:
                changeState(GameState.PACKAGING);
                break;
            case PACKAGING:
                changeState(GameState.COMPLETE);
                break;
            case COMPLETE:
                generateNewCustomer();
                resetGame();
                break;
        }
    }

    public void startBaking() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startBaking'");
    }
}


