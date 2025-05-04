// Package declaration
package com.cakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main application class for the Bakery Simulation Game
 */
public class BakeryGame extends Application {
    
    // Game controller instance
    private GameController gameController;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize game controller
        gameController = new GameController();
        
        // Create the main game scene
        Scene scene = gameController.createGameScene();
        
        // Set up the stage
        primaryStage.setTitle("Cake Bakery Simulation");
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * Main controller class that manages game logic and interactions
 */
public class GameController {
    // Game components
    private Pane gamePane;
    private GameState gameState;
    
    // Game objects
    private List<Ingredient> availableIngredients;
    private List<Appliance> appliances;
    private Cake currentCake;
    private DecoratingStation decoratingStation;
    
    // UI Elements (will be handled mainly by your UI teammate)
    private Map<String, ImageView> ingredientViews;
    private Map<String, ImageView> applianceViews;
    
    /**
     * Constructor initializes the game
     */
    public GameController() {
        gameState = new GameState();
        initializeGameObjects();
    }
    
    /**
     * Create and return the main game scene
     */
    public Scene createGameScene() {
        gamePane = new Pane();
        
        // Setup game elements (your UI teammate will handle detailed visuals)
        setupGameElements();
        
        // Setup event handlers for drag-drop (coordinate with UI teammate)
        setupEventHandlers();
        
        return new Scene(gamePane);
    }
    
    /**
     * Initialize game objects and collections
     */
    private void initializeGameObjects() {
        // Initialize ingredients
        availableIngredients = new ArrayList<>();
        availableIngredients.add(new Ingredient("CakeMix", "White"));
        availableIngredients.add(new Ingredient("CakeMix", "Chocolate"));
        availableIngredients.add(new Ingredient("Egg", "Regular"));
        availableIngredients.add(new Ingredient("Milk", "Regular"));
        
        // Initialize appliances
        appliances = new ArrayList<>();
        appliances.add(new Mixer());
        appliances.add(new Oven());
        appliances.add(new CakeTin("Round"));
        
        // Initialize decorating station
        decoratingStation = new DecoratingStation();
        
        // Initialize UI element maps
        ingredientViews = new HashMap<>();
        applianceViews = new HashMap<>();
    }
    
    /**
     * Setup game visual elements (coordinate with UI teammate)
     */
    private void setupGameElements() {
        // This will be mostly handled by your UI teammate
        // But you'll need to link visual elements to your logic objects
    }
    
    /**
     * Setup event handlers for game interactions
     */
    private void setupEventHandlers() {
        // These will connect UI events to your game logic
        // Coordinate with your teammate who's handling drag and drop
    }
    
    /**
     * Process ingredient being added to an appliance
     */
    public boolean addIngredientToAppliance(Ingredient ingredient, Appliance appliance) {
        // Check if this is a valid combination
        if (appliance instanceof Mixer) {
            return ((Mixer) appliance).addIngredient(ingredient);
        }
        return false;
    }
    
    /**
     * Process cake being moved between stations
     */
    public boolean moveCakeBetweenStations(Cake cake, Appliance source, Appliance destination) {
        // Validate the movement is allowed
        if (source instanceof Mixer && destination instanceof CakeTin) {
            if (((Mixer) source).isMixComplete()) {
                currentCake = ((Mixer) source).pourIntoTin((CakeTin) destination);
                return true;
            }
        } else if (source instanceof CakeTin && destination instanceof Oven) {
            if (currentCake != null && currentCake.isReadyToBake()) {
                ((Oven) destination).placeCake(currentCake);
                return true;
            }
        } else if (source instanceof Oven && destination instanceof DecoratingStation) {
            if (((Oven) source).isBakingComplete()) {
                currentCake = ((Oven) source).removeCake();
                decoratingStation.setCake(currentCake);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Apply decoration to cake
     */
    public boolean decorateCake(String decorationType, String decorationStyle) {
        if (currentCake != null && currentCake.isBaked()) {
            return decoratingStation.applyDecoration(decorationType, decorationStyle);
        }
        return false;
    }
    
    /**
     * Complete the cake and deliver to customer
     */
    public boolean packageAndDeliverCake() {
        if (currentCake != null && currentCake.isFullyDecorated()) {
            // Package the cake
            currentCake.setPackaged(true);
            
            // Deliver to customer
            boolean customerSatisfied = evaluateCustomerSatisfaction();
            
            // Reset game state for next cake
            resetGameState();
            
            return customerSatisfied;
        }
        return false;
    }
    
    /**
     * Evaluate if customer is satisfied with the cake
     */
    private boolean evaluateCustomerSatisfaction() {
        // This could be enhanced to check if cake matches customer preferences
        return currentCake.isFullyDecorated() && currentCake.isBaked();
    }
    
    /**
     * Reset game state for next cake
     */
    private void resetGameState() {
        currentCake = null;
        for (Appliance appliance : appliances) {
            appliance.reset();
        }
        decoratingStation.reset();
    }

    public Appliance getApplianceByType(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getApplianceByType'");
    }

    public void exitGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exitGame'");
    }

    public void addObserver(MainSceneController mainSceneController) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }
}

/**
 * Class representing game ingredients
 */
public class Ingredient {
    private String type;
    private String variant;
    
    public Ingredient(String type, String variant) {
        this.type = type;
        this.variant = variant;
    }
    
    public String getType() {
        return type;
    }
    
    public String getVariant() {
        return variant;
    }
    
    @Override
    public String toString() {
        return variant + " " + type;
    }

    public static String[] keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    public String getImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImagePath'");
    }
}

/**
 * Base class for all kitchen appliances
 */
abstract class Appliance {
    protected String type;
    protected boolean inUse;
    
    public Appliance(String type) {
        this.type = type;
        this.inUse = false;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public abstract void reset();
}

/**
 * Mixer appliance for combining ingredients
 */
class Mixer extends Appliance {
    private List<Ingredient> ingredients;
    private boolean mixComplete;
    
    public Mixer() {
        super("Mixer");
        ingredients = new ArrayList<>();
        mixComplete = false;
    }
    
    public boolean addIngredient(Ingredient ingredient) {
        // Validation logic - check if this ingredient can be added
        if (isValidIngredient(ingredient)) {
            ingredients.add(ingredient);
            return true;
        }
        return false;
    }
    
    private boolean isValidIngredient(Ingredient ingredient) {
        // Logic to check if this ingredient is valid for the mixer
        String type = ingredient.getType();
        return type.equals("CakeMix") || type.equals("Egg") || type.equals("Milk");
    }
    
    public boolean mix() {
        // Check if we have all required ingredients
        boolean hasCakeMix = false;
        boolean hasEgg = false;
        boolean hasMilk = false;
        
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals("CakeMix")) hasCakeMix = true;
            if (ingredient.getType().equals("Egg")) hasEgg = true;
            if (ingredient.getType().equals("Milk")) hasMilk = true;
        }
        
        // Require all three ingredient types to complete mix
        if (hasCakeMix && hasEgg && hasMilk) {
            mixComplete = true;
            inUse = true;
            return true;
        }
        return false;
    }
    
    public boolean isMixComplete() {
        return mixComplete;
    }
    
    public Cake pourIntoTin(CakeTin tin) {
        if (mixComplete && !ingredients.isEmpty()) {
            // Determine cake type from ingredients
            String cakeType = "White"; // Default
            
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getType().equals("CakeMix") && 
                    ingredient.getVariant().equals("Chocolate")) {
                    cakeType = "Chocolate";
                    break;
                }
            }
            
            // Create new cake and reset the mixer
            Cake cake = new Cake(cakeType);
            reset();
            return cake;
        }
        return null;
    }
    
    @Override
    public void reset() {
        ingredients.clear();
        mixComplete = false;
        inUse = false;
    }
}

/**
 * Cake tin appliance for shaping cake
 */
class CakeTin extends Appliance {
    private String shape;
    private Cake cake;
    
    public CakeTin(String shape) {
        super("CakeTin");
        this.shape = shape;
    }
    
    public String getShape() {
        return shape;
    }
    
    public void placeCake(Cake cake) {
        this.cake = cake;
        cake.setShape(shape);
        inUse = true;
    }
    
    public Cake removeCake() {
        Cake removedCake = cake;
        cake = null;
        inUse = false;
        return removedCake;
    }
    
    @Override
    public void reset() {
        cake = null;
        inUse = false;
    }
}

/**
 * Oven appliance for baking cake
 */
class Oven extends Appliance {
    private Cake cake;
    private boolean bakingComplete;
    
    public Oven() {
        super("Oven");
        bakingComplete = false;
    }
    
    public void placeCake(Cake cake) {
        if (cake != null && cake.isReadyToBake()) {
            this.cake = cake;
            inUse = true;
        }
    }
    
    public boolean bake() {
        if (cake != null) {
            cake.setBaked(true);
            bakingComplete = true;
            return true;
        }
        return false;
    }
    
    public boolean isBakingComplete() {
        return bakingComplete;
    }
    
    public Cake removeCake() {
        if (bakingComplete) {
            Cake bakedCake = cake;
            reset();
            return bakedCake;
        }
        return null;
    }
    
    @Override
    public void reset() {
        cake = null;
        bakingComplete = false;
        inUse = false;
    }
}

/**
 * Class representing the cake in various states
 */
class Cake {
    private String type;
    private String shape;
    private boolean isBaked;
    private boolean isPackaged;
    
    // Decoration properties
    private String icingType;
    private String borderStyle;
    private List<String> toppings;
    private boolean hasDrip;
    
    public Cake(String type) {
        this.type = type;
        this.shape = null;
        this.isBaked = false;
        this.isPackaged = false;
        this.toppings = new ArrayList<>();
    }
    
    public String getType() {
        return type;
    }
    
    public void setShape(String shape) {
        this.shape = shape;
    }
    
    public String getShape() {
        return shape;
    }
    
    public boolean isReadyToBake() {
        return shape != null;
    }
    
    public void setBaked(boolean baked) {
        isBaked = baked;
    }
    
    public boolean isBaked() {
        return isBaked;
    }
    
    public void setIcing(String icingType) {
        this.icingType = icingType;
    }
    
    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }
    
    public void addTopping(String topping) {
        toppings.add(topping);
    }
    
    public void setDrip(boolean hasDrip) {
        this.hasDrip = hasDrip;
    }
    
    public boolean isFullyDecorated() {
        // A cake is fully decorated if it has icing
        return icingType != null;
    }
    
    public void setPackaged(boolean packaged) {
        isPackaged = packaged;
    }
    
    public boolean isPackaged() {
        return isPackaged;
    }
}

/**
 * Class representing the decorating station
 */
class DecoratingStation {
    private Cake cake;
    
    public DecoratingStation() {
        cake = null;
    }
    
    public void setCake(Cake cake) {
        this.cake = cake;
    }
    
    public boolean applyDecoration(String decorationType, String decorationStyle) {
        if (cake == null || !cake.isBaked()) {
            return false;
        }
        
        switch (decorationType) {
            case "Icing":
                cake.setIcing(decorationStyle); // White, Pink, Chocolate
                return true;
            case "Border":
                cake.setBorderStyle(decorationStyle); // Different tip styles
                return true;
            case "Topping":
                cake.addTopping(decorationStyle); // Strawberries, Cookies, Sprinkles
                return true;
            case "Drip":
                cake.setDrip(decorationStyle.equals("On"));
                return true;
            default:
                return false;
        }
    }
    
    public Cake completeCake() {
        if (cake != null && cake.isFullyDecorated()) {
            Cake decoratedCake = cake;
            cake = null;
            return decoratedCake;
        }
        return null;
    }
    
    public void reset() {
        cake = null;
    }
}

/**
 * Class for tracking game state
 */
class GameState {
    private int score;
    private int level;
    private int completedCakes;
    
    public GameState() {
        score = 0;
        level = 1;
        completedCakes = 0;
    }
    
    public void increaseScore(int points) {
        score += points;
        checkLevelProgress();
    }
    
    public void cakeCompleted(boolean customerSatisfied) {
        completedCakes++;
        
        if (customerSatisfied) {
            increaseScore(100);
        }
        
        checkLevelProgress();
    }
    
    private void checkLevelProgress() {
        // Simple level progression - every 5 cakes
        if (completedCakes > 0 && completedCakes % 5 == 0) {
            level++;
        }
    }
    
    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getCompletedCakes() {
        return completedCakes;
    }
}
        
