// MainSceneController.java 
package com.cakegame;

import com.cakegame.controller.GameController;
import com.cakegame.controller.CustomerController;
import com.cakegame.enums.*;
import com.cakegame.model.Cake;
import com.cakegame.model.Customer;
import com.cakegame.model.GameStateManager;
import com.example.util.SoundManager;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Controller for the main game scene FXML with drag and drop functionality
 */
public class MainSceneController implements GameStateManager.GameStateObserver {
    private static final GameState MAIN_MENU = null;
    private static final GameState PLAYING = null;
    private static final GameState PAUSED = null;
    private static final GameState GAME_OVER = null;
    private static final GameState LEVEL_COMPLETE = null;
    // FXML injected controls
    @FXML private AnchorPane mainContainer;
    @FXML private Pane gameContainer;
    @FXML private StackPane stationContainer;
    @FXML private Label scoreLabel;
    @FXML private Label timeLabel;
    @FXML private ProgressBar timeProgressBar;
    
    // References to station controllers
    private MixingStationController mixingStationController;
    private BakingStationController bakingStationController;
    private DecoratingStationController decoratingStationController;
    private BakingStationController boxingStationController;
    private CustomerController customerStationController;
    
    private GameController gameController;
    
    public void initializeGame(GameController gameController) {
        this.gameController = gameController;
        
        // Register as observer
        gameController.addObserver(this);
        
        // Initialize station controllers
        initializeStationControllers();
        
        // Start with main menu
        showMainMenu();
    }
    
    private void initializeStationControllers() {
        // These would be loaded from FXML files
        // For now, we'll just create empty placeholders
        mixingStationController = new MixingStationController();
        bakingStationController = new BakingStationController();
        decoratingStationController = new DecoratingStationController();
        boxingStationController = new BakingStationController();
        customerStationController = new CustomerController(null);
        
        // Initialize stations with game controller
        mixingStationController.initialize(gameController);
        bakingStationController.initialize(gameController);
        decoratingStationController.initialize(gameController);
        boxingStationController.initialize(gameController);
        customerStationController.initialize(gameController);
    }
    
    private void showMainMenu() {
        // TODO: Show main menu UI
    }
    
    @FXML
    private void startGame(ActionEvent event) {
        // Start game with medium difficulty
        gameController.startNewGame(GameDifficulty.MEDIUM);
        SoundManager.getInstance().playSound("button_click");
    }
    
    @FXML
    private void pauseGame(ActionEvent event) {
        gameController.pauseGame();
        SoundManager.getInstance().playSound("button_click");
        // TODO: Show pause menu
    }
    
    @FXML
    private void exitGame(ActionEvent event) {
        gameController.exitGame();
        Platform.exit();
    }
    
    // Show the appropriate station based on current game state
    private void showStation(StationState station) {
        // Clear current station
        stationContainer.getChildren().clear();
        
        // Add new station
        switch (station) {
            case MIXING_STATION:
                stationContainer.getChildren().add(mixingStationController.getView());
                mixingStationController.refresh();
                break;
            case POURING_STATION:
            case BAKING_STATION:
                stationContainer.getChildren().add(bakingStationController.getView());
                bakingStationController.refresh();
                break;
            case DECORATING_STATION:
                stationContainer.getChildren().add(decoratingStationController.getView());
                decoratingStationController.refresh();
                break;
            case BOXING_STATION:
                stationContainer.getChildren().add(boxingStationController.getView());
                boxingStationController.refresh();
                break;
            case CUSTOMER_STATION:
                stationContainer.getChildren().add(customerStationController.getView());
                customerStationController.refresh();
                break;
        }
        
        // Animate transition
        FadeTransition ft = new FadeTransition(Duration.millis(300), stationContainer);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    // Observer methods
    @Override
    public void onGameStateChanged(GameState newState) {
        Platform.runLater(() -> {
            switch (newState) {
                case MAIN_MENU:
                    showMainMenu();
                    break;
                case PLAYING:
                    // Already showing game
                    break;
                case PAUSED:
                    // Show pause menu
                    break;
                case GAME_OVER:
                    showGameOver();
                    break;
                case LEVEL_COMPLETE:
                    showLevelComplete();
                    break;
            }
        });
    }
    
    @Override
    public void onStationChanged(StationState newStation) {
        Platform.runLater(() -> {
            showStation(newStation);
        });
    }
    
    @Override
    public void onScoreChanged(int newScore) {
        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + newScore);
        });
    }
    
    @Override
    public void onTimeChanged(int timeRemaining) {
        Platform.runLater(() -> {
            // Format time as mm:ss
            int minutes = timeRemaining / 60;
            int seconds = timeRemaining % 60;
            timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            
            // Update progress bar
            GameStateManager gameState = gameController.getGameState();
            int totalTime = gameState.getDifficulty().getTimeLimit();
            timeProgressBar.setProgress((double)timeRemaining / totalTime);
        });
    }
    
    private void showGameOver() {
        // TODO: Show game over screen
    }
    
    private void showLevelComplete() {
        // TODO: Show level complete screen
    }
    
    /**
     * Utility method to create a draggable ingredient
     */
    private ImageView createDraggableImageView(String imagePath, String ingredientId, double x, double y, double width, double height) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setId(ingredientId);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setCursor(javafx.scene.Cursor.HAND);
        
        // Setup drag detection
        imageView.setOnDragDetected(event -> {
            // Begin drag operation
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            
            // Put a string on dragboard that identifies this ingredient
            ClipboardContent content = new ClipboardContent();
            content.putString(ingredientId);
            db.setContent(content);
            
            // Create a snapshot of the image for drag visualization
            db.setDragView(imageView.snapshot(null, null));
            
            event.consume();
        });
        
        return imageView;
    }
    
    /**
     * Mixing Station Controller with drag-and-drop support
     */
    private class MixingStationController {
        private Pane view;
        private ImageView bowlImageView;
        private ImageView cakeMixImageView;
        private ImageView eggImageView;
        private ImageView milkImageView;
        private Button whiteFavorButton;
        private Button chocolateFlavorButton;
        private Button mixButton;
        private Label statusLabel;
        
        // Tracking which ingredients have been added
        private boolean cakeMixAdded = false;
        private boolean eggAdded = false;
        private boolean milkAdded = false;
        private CakeFlavor selectedFlavor = null;
        
        public void initialize(GameController gameController) {
            // Create view
            view = new Pane();
            view.setPrefSize(600, 400);
            view.setStyle("-fx-background-color: #f0f0f0;");
            
            // Create mixing bowl (drop target)
            bowlImageView = new ImageView(new Image("images/mixing_bowl.png")); // Replace with actual image path
            bowlImageView.setLayoutX(300);
            bowlImageView.setLayoutY(150);
            bowlImageView.setFitWidth(200);
            bowlImageView.setFitHeight(150);
            
            // Create draggable ingredients
            cakeMixImageView = createDraggableImageView("images/cake_mix.png", "cake_mix", 50, 100, 80, 80);
            eggImageView = createDraggableImageView("images/egg.png", "egg", 50, 200, 60, 80);
            milkImageView = createDraggableImageView("images/milk.png", "milk", 50, 300, 70, 100);
            
            // Create flavor buttons
            whiteFavorButton = createFlavorButton("White", 500, 100);
            chocolateFlavorButton = createFlavorButton("Chocolate", 500, 150);
            
            mixButton = new Button("Mix Ingredients");
            mixButton.setLayoutX(350);
            mixButton.setLayoutY(320);
            mixButton.setPrefSize(150, 50);
            mixButton.setDisable(true); // Disabled until all ingredients added
            
            statusLabel = new Label("Drag ingredients into the bowl");
            statusLabel.setLayoutX(50);
            statusLabel.setLayoutY(360);
            
            // Set up drop handling for the bowl
            setupBowlDropHandling();
            
            // Add event handlers for flavor buttons
            whiteFavorButton.setOnAction(e -> {
                selectFlavor(CakeFlavor.WHITE);
                whiteFavorButton.setStyle("-fx-background-color: #8aff8a;");
                chocolateFlavorButton.setStyle("");
                updateMixButtonState();
                SoundManager.getInstance().playSound("button_click");
            });
            
            chocolateFlavorButton.setOnAction(e -> {
                selectFlavor(CakeFlavor.CHOCOLATE);
                chocolateFlavorButton.setStyle("-fx-background-color: #8aff8a;");
                whiteFavorButton.setStyle("");
                updateMixButtonState();
                SoundManager.getInstance().playSound("button_click");
            });
            
            mixButton.setOnAction(e -> {
                mixIngredients();
                SoundManager.getInstance().playSound("mixer");
            });
            
            // Add elements to view
            view.getChildren().addAll(
                bowlImageView,
                cakeMixImageView, 
                eggImageView, 
                milkImageView, 
                whiteFavorButton, 
                chocolateFlavorButton, 
                mixButton, 
                statusLabel
            );
        }
        
        private void setupBowlDropHandling() {
            // Handle drag over
            bowlImageView.setOnDragOver(event -> {
                if (event.getGestureSource() != bowlImageView && 
                    event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            });
            
            // Handle drop
            bowlImageView.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                
                if (db.hasString()) {
                    String ingredientId = db.getString();
                    addIngredientToBowl(ingredientId);
                    success = true;
                }
                
                event.setDropCompleted(success);
                event.consume();
            });
        }
        
        private void addIngredientToBowl(String ingredientId) {
            // Determine which ingredient was dropped
            switch (ingredientId) {
                case "cake_mix":
                    if (!cakeMixAdded) {
                        cakeMixAdded = true;
                        animateIngredientToBowl(cakeMixImageView);
                        gameController.addIngredient("Cake Mix");
                        SoundManager.getInstance().playSound("ingredient_add");
                    }
                    break;
                case "egg":
                    if (!eggAdded) {
                        eggAdded = true;
                        animateIngredientToBowl(eggImageView);
                        gameController.addIngredient("Egg");
                        SoundManager.getInstance().playSound("ingredient_add");
                    }
                    break;
                case "milk":
                    if (!milkAdded) {
                        milkAdded = true;
                        animateIngredientToBowl(milkImageView);
                        gameController.addIngredient("Milk");
                        SoundManager.getInstance().playSound("ingredient_add");
                    }
                    break;
            }
            
            updateMixButtonState();
        }
        
        private void animateIngredientToBowl(ImageView ingredientView) {
            // Create a clone of the ingredient for the animation
            ImageView clonedIngredient = new ImageView(ingredientView.getImage());
            clonedIngredient.setFitWidth(ingredientView.getFitWidth());
            clonedIngredient.setFitHeight(ingredientView.getFitHeight());
            clonedIngredient.setLayoutX(ingredientView.getLayoutX());
            clonedIngredient.setLayoutY(ingredientView.getLayoutY());
            
            // Add the clone to the view
            view.getChildren().add(clonedIngredient);
            
            // Calculate target position (center of bowl)
            double targetX = bowlImageView.getLayoutX() + bowlImageView.getFitWidth()/2 - clonedIngredient.getFitWidth()/2;
            double targetY = bowlImageView.getLayoutY() + bowlImageView.getFitHeight()/2 - clonedIngredient.getFitHeight()/2;
            
            // Create animation
            TranslateTransition tt = new TranslateTransition(Duration.millis(500), clonedIngredient);
            tt.setToX(targetX - clonedIngredient.getLayoutX());
            tt.setToY(targetY - clonedIngredient.getLayoutY());
            
            // When animation finishes, remove the cloned ingredient (it's in the bowl now)
            tt.setOnFinished(e -> {
                view.getChildren().remove(clonedIngredient);
                
                // Modify the original ingredient to appear "used"
                ingredientView.setOpacity(0.5);
                ingredientView.setDisable(true);
            });
            
            tt.play();
        }
        
        private Button createFlavorButton(String text, double x, double y) {
            Button button = new Button(text + " Flavor");
            button.setLayoutX(x);
            button.setLayoutY(y);
            button.setPrefSize(120, 40);
            return button;
        }
        
        private void selectFlavor(CakeFlavor flavor) {
            selectedFlavor = flavor;
            gameController.setFlavor(flavor);
        }
        
        private void updateMixButtonState() {
            // Check if all ingredients are added and flavor is selected
            mixButton.setDisable(!(cakeMixAdded && eggAdded && milkAdded && selectedFlavor != null));
            
            if (mixButton.isDisabled()) {
                statusLabel.setText("Add all ingredients and select a flavor");
            } else {
                statusLabel.setText("Ready to mix!");
            }
        }
        
        private void mixIngredients() {
            statusLabel.setText("Mixing in progress...");
            mixButton.setDisable(true);
            
            // Animation for mixing would go here
            
            // Notify game controller
            gameController.mixIngredients();
        }
        
        public Pane getView() {
            return view;
        }
        
        public void refresh() {
            // Reset UI based on current game state
            cakeMixAdded = false;
            eggAdded = false;
            milkAdded = false;
            selectedFlavor = null;
            
            cakeMixImageView.setOpacity(1.0);
            cakeMixImageView.setDisable(false);
            eggImageView.setOpacity(1.0);
            eggImageView.setDisable(false);
            milkImageView.setOpacity(1.0);
            milkImageView.setDisable(false);
            
            whiteFavorButton.setStyle("");
            chocolateFlavorButton.setStyle("");
            mixButton.setDisable(true);
            statusLabel.setText("Drag ingredients into the bowl");
        }
    }
    
    /**
     * Baking Station Controller with drag-and-drop support
     */
    private class BakingStationController {
        private Pane view;
        private ImageView ovenImageView;
        private ImageView cakeTinImageView;
        private ImageView batterContainerImageView;
        private Button bakeButton;
        private ProgressBar bakingProgress;
        private Label statusLabel;
        private boolean isPoured = false;
        private boolean isBaking = false;
        
        public void initialize(GameController gameController) {
            view = new Pane();
            view.setPrefSize(600, 400);
            view.setStyle("-fx-background-color: #f0f0f0;");
            
            // Create oven visualization
            ovenImageView = new ImageView(new Image("images/oven.png")); // Replace with actual image path
            ovenImageView.setLayoutX(350);
            ovenImageView.setLayoutY(100);
            ovenImageView.setFitWidth(200);
            ovenImageView.setFitHeight(200);
            
            // Create cake tin (drop target)
            cakeTinImageView = new ImageView(new Image("images/cake_tin.png")); // Replace with actual image path
            cakeTinImageView.setLayoutX(150);
            cakeTinImageView.setLayoutY(150);
            cakeTinImageView.setFitWidth(150);
            cakeTinImageView.setFitHeight(100);
            
            // Create batter container (draggable)
            batterContainerImageView = createDraggableImageView("images/batter_bowl.png", "batter", 50, 150, 100, 100);
            
            bakeButton = new Button("Start Baking");
            bakeButton.setLayoutX(350);
            bakeButton.setLayoutY(320);
            bakeButton.setPrefSize(150, 50);
            bakeButton.setDisable(true); // Disabled until batter is poured
            
            bakingProgress = new ProgressBar(0);
            bakingProgress.setLayoutX(200);
            bakingProgress.setLayoutY(320);
            bakingProgress.setPrefWidth(200);
            bakingProgress.setVisible(false);
            
            statusLabel = new Label("Pour batter into cake tin");
            statusLabel.setLayoutX(50);
            statusLabel.setLayoutY(360);
            
            // Setup drop targets for the cake tin
            setupCakeTinDropHandling();
            
            // Add event handler for bake button
            bakeButton.setOnAction(e -> {
                startBaking();
                SoundManager.getInstance().playSound("oven_door");
            });
            
            // Add elements to view
            view.getChildren().addAll(
                ovenImageView,
                cakeTinImageView,
                batterContainerImageView,
                bakeButton,
                bakingProgress,
                statusLabel
            );
        }
        
        private void setupCakeTinDropHandling() {
            // Handle drag over
            cakeTinImageView.setOnDragOver(event -> {
                if (event.getGestureSource() != cakeTinImageView && 
                    event.getDragboard().hasString() &&
                    !isPoured) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            });
            
            // Handle drop
            cakeTinImageView.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                
                if (db.hasString() && db.getString().equals("batter") && !isPoured) {
                    pourBatterIntoTin();
                    success = true;
                }
                
                event.setDropCompleted(success);
                event.consume();
            });
        }
        
        private void pourBatterIntoTin() {
            isPoured = true;
            
            // Animate batter pouring
            TranslateTransition tt = new TranslateTransition(Duration.millis(800), batterContainerImageView);
            tt.setToX(cakeTinImageView.getLayoutX() - batterContainerImageView.getLayoutX() + 25);
            tt.setToY(cakeTinImageView.getLayoutY() - batterContainerImageView.getLayoutY() - 50);
            
            tt.setOnFinished(e -> {
                // Change cake tin appearance to show it has batter
                cakeTinImageView.setImage(new Image("images/cake_tin_filled.png")); // Replace with actual image
                
                // Return batter container to original position
                batterContainerImageView.setTranslateX(0);
                batterContainerImageView.setTranslateY(0);
                batterContainerImageView.setOpacity(0.5);
                batterContainerImageView.setDisable(true);
                
                // Enable bake button
                bakeButton.setDisable(false);
                statusLabel.setText("Ready to bake");
                
                SoundManager.getInstance().playSound("pour");
            });
            
            tt.play();
        }
        
        private void startBaking() {
            if (!isPoured) return;
            
            isBaking = true;
            bakeButton.setDisable(true);
            bakingProgress.setVisible(true);
            statusLabel.setText("Baking in progress...");
            
            // Simulate baking process
            gameController.startBaking();
            
            // Animation to show cake tin going into oven would go here
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), cakeTinImageView);
            tt.setToX(ovenImageView.getLayoutX() - cakeTinImageView.getLayoutX() + 25);
            tt.setToY(ovenImageView.getLayoutY() - cakeTinImageView.getLayoutY() + 50);
            tt.play();
        }
        
        public void updateBakingProgress(double progress) {
            bakingProgress.setProgress(progress);
            
            if (progress >= 1.0) {
                bakingComplete();
            }
        }
        
        private void bakingComplete() {
            isBaking = false;
            statusLabel.setText("Cake is ready!");
            
            // Animation to show cake tin coming out of oven would go here
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), cakeTinImageView);
            tt.setToX(0);
            tt.setToY(0);
            
            tt.setOnFinished(e -> {
                // Change cake tin image to show baked cake
                cakeTinImageView.setImage(new Image("images/cake_baked.png")); // Replace with actual image
                gameController.finishBaking();
            });
            
            tt.play();
        }
        
        public Pane getView() {
            return view;
        }
        
        public void refresh() {
            isPoured = false;
            isBaking = false;
            batterContainerImageView.setOpacity(1.0);
            batterContainerImageView.setDisable(false);
            cakeTinImageView.setImage(new Image("images/cake_tin.png")); // Reset to empty tin
            cakeTinImageView.setTranslateX(0);
            cakeTinImageView.setTranslateY(0);
            bakeButton.setDisable(true);
            bakingProgress.setProgress(0);
            bakingProgress.setVisible(false);
            statusLabel.setText("Pour batter into cake tin");
        }
    }
    
    private class DecoratingStationController {
        private Pane view;
        private ImageView cakeImageView;
        private ImageView whiteIcingImageView;
        private ImageView pinkIcingImageView;
        private ImageView chocolateIcingImageView;
        private ImageView strawberriesImageView;
        private ImageView cookiesImageView;
        private ImageView sprinklesImageView;
        private ImageView dripImageView;
        private Button roundTipButton;
        private Button starTipButton;
        private Button finishButton;
        private Label statusLabel;
        
        // Tracking decoration state
        private IcingType appliedIcing = null;
        private BorderStyle appliedBorderTip = null;
        private boolean hasDrip = false;
        private boolean hasStrawberries = false;
        private boolean hasCookies = false;
        private boolean hasSprinkles = false;
        
        public void initialize(GameController gameController) {
            view = new Pane();
            view.setPrefSize(600, 400);
            view.setStyle("-fx-background-color: #f0f0f0;");
            
            // Create cake visualization (drop target)
            cakeImageView = new ImageView(new Image("images/plain_cake.png")); // Replace with actual image
            cakeImageView.setLayoutX(300);
            cakeImageView.setLayoutY(150);
            cakeImageView.setFitWidth(200);
            cakeImageView.setFitHeight(150);
            
            // Create draggable decorations
            whiteIcingImageView = createDraggableImageView("images/white_icing.png", "white_icing", 50, 50, 80, 60);
            pinkIcingImageView = createDraggableImageView("images/pink_icing.png", "pink_icing", 50, 120, 80, 60);
            chocolateIcingImageView = createDraggableImageView("images/chocolate_icing.png", "chocolate_icing", 50, 190, 80, 60);
            
            strawberriesImageView = createDraggableImageView("images/strawberries.png", "strawberries", 480, 50, 80, 60);
            cookiesImageView = createDraggableImageView("images/cookies.png", "cookies", 480, 120, 80, 60);
            sprinklesImageView = createDraggableImageView("images/sprinkles.png", "sprinkles", 480, 190, 80, 60);
            
            dripImageView = createDraggableImageView("images/drip.png", "drip", 480, 260, 80, 60);
            
            // Create buttons for piping tips
            roundTipButton = createDecoratingButton("Round Tip", 50, 260);
            starTipButton = createDecoratingButton("Star Tip", 50, 310);
            
            // Finish button
            finishButton = new Button("Finish Decorating");
            finishButton.setLayoutX(225);
            finishButton.setLayoutY(340);
            finishButton.setPrefSize(150, 40);
            finishButton.setDisable(true); // Disabled until at least icing is applied
            
            statusLabel = new Label("Drag icing onto the cake to start decorating");
            statusLabel.setLayoutX(180);
            statusLabel.setLayoutY(20);
            
            // Setup drop handling for the cake
            setupCakeDropHandling();
            
            // Add event handlers for piping tip buttons
            roundTipButton.setOnAction(e -> {
                applyBorderStyle(BorderStyle.ROUND);
                highlightSelectedButton(roundTipButton, new Button[]{starTipButton});
                SoundManager.getInstance().playSound("decoration");
            });
            
            starTipButton.setOnAction(e -> {
                applyBorderStyle(BorderStyle.STAR);
                highlightSelectedButton(starTipButton, new Button[]{roundTipButton});
                SoundManager.getInstance().playSound("decoration");
            });
            
            finishButton.setOnAction(e -> {
                finishDecorating();
                SoundManager.getInstance().playSound("complete");
            });
            
            // Add elements to view
            view.getChildren().addAll(
                cakeImageView,
                whiteIcingImageView,
                pinkIcingImageView,
                chocolateIcingImageView,
                strawberriesImageView,
                cookiesImageView,
                sprinklesImageView,
                dripImageView,
                roundTipButton,
                starTipButton,
                finishButton,
                statusLabel
            );
        }
        
        private void applyBorderStyle(BorderStyle star) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'applyBorderStyle'");
        }

        private void setupCakeDropHandling() {
            // Handle drag over
            cakeImageView.setOnDragOver(event -> {
                if (event.getGestureSource() != cakeImageView && 
                    event.getDragboard().hasString()) {
                    
                    String draggedId = event.getDragboard().getString();
                    
                    // Only allow icing if no icing is applied yet
                    if ((draggedId.endsWith("_icing") && appliedIcing == null) ||
                        // Only allow toppings if icing is applied
                        (!draggedId.endsWith("_icing") && appliedIcing != null)) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                }
                event.consume();
            });
            
            // Handle drop
            cakeImageView.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                
                if (db.hasString()) {
                    String decorationId = db.getString();
                    applyDecoration(decorationId);
                    success = true;
                }
                
                event.setDropCompleted(success);
                event.consume();
            });
        }
        
        private Button createDecoratingButton(String text, double x, double y) {
            Button button = new Button(text);
            button.setLayoutX(x);
            button.setLayoutY(y);
            button.setPrefSize(100, 40);
            return button;
        }
        
        private void highlightSelectedButton(Button selected, Button[] others) {
            selected.setStyle("-fx-background-color: #8aff8a;");
            for (Button btn : others) {
                btn.setStyle("");
            }
        }
        
        private void applyDecoration(String decorationId) {
            switch (decorationId) {
                case "white_icing":
                    applyIcing(IcingType.WHITE);
                    whiteIcingImageView.setOpacity(0.5);
                    whiteIcingImageView.setDisable(true);
                    SoundManager.getInstance().playSound("icing");
                    break;
                case "pink_icing":
                    applyIcing(IcingType.PINK);
                    pinkIcingImageView.setOpacity(0.5);
                    pinkIcingImageView.setDisable(true);
                    SoundManager.getInstance().playSound("icing");
                    break;
                case "chocolate_icing":
                    applyIcing(IcingType.CHOCOLATE);
                    chocolateIcingImageView.setOpacity(0.5);
                    chocolateIcingImageView.setDisable(true);
                    SoundManager.getInstance().playSound("icing");
                    break;
                case "strawberries":
                    if (!hasStrawberries) {
                        hasStrawberries = true;
                        addTopping("strawberries");
                        strawberriesImageView.setOpacity(0.5);
                        strawberriesImageView.setDisable(true);
                        SoundManager.getInstance().playSound("decoration");
                    }
                    break;
                case "cookies":
                    if (!hasCookies) {
                        hasCookies = true;
                        addTopping("cookies");
                        cookiesImageView.setOpacity(0.5);
                        cookiesImageView.setDisable(true);
                        SoundManager.getInstance().playSound("decoration");
                    }
                    break;
                case "sprinkles":
                    if (!hasSprinkles) {
                        hasSprinkles = true;
                        addTopping("sprinkles");
                        sprinklesImageView.setOpacity(0.5);
                        sprinklesImageView.setDisable(true);
                        SoundManager.getInstance().playSound("decoration");
                    }
                    break;
                case "drip":
                    if (!hasDrip) {
                        hasDrip = true;
                        addTopping("drip");
                        dripImageView.setOpacity(0.5);
                        dripImageView.setDisable(true);
                        SoundManager.getInstance().playSound("decoration");
                    }
                    break;
            }
            
            updateFinishButtonState();
        }
        
        private void applyIcing(IcingType icing) {
            appliedIcing = icing;
            gameController.setIcingType(icing);
            
            // Update cake image based on icing type
            switch (icing) {
                case WHITE:
                    cakeImageView.setImage(new Image("images/cake_white_icing.png"));
                    break;
                case PINK:
                    cakeImageView.setImage(new Image("images/cake_pink_icing.png"));
                    break;
                case CHOCOLATE:
                    cakeImageView.setImage(new Image("images/cake_chocolate_icing.png"));
                    break;
            }
            
            statusLabel.setText("Icing applied! Add toppings or apply border decoration.");
        }
        
        private void applyBorderTip(BorderStyle tip) {
            if (appliedIcing == null) {
                statusLabel.setText("You need to apply icing first!");
                return;
            }
            
            appliedBorderTip = tip;
            gameController.setBorderStyle(tip);
            
            // Update cake image with border
            String imagePath = "images/cake_" + appliedIcing.toString().toLowerCase() + "_" + 
                              tip.toString().toLowerCase() + "_border.png";
            cakeImageView.setImage(new Image(imagePath));
            
            statusLabel.setText("Border applied! Continue decorating or finish.");
            updateFinishButtonState();
        }
        
        private void addTopping(String toppingType) {
            if (appliedIcing == null) {
                statusLabel.setText("You need to apply icing first!");
                return;
            }
            
            gameController.addToppingType(toppingType);
            
            // In a real implementation, we would add the topping visually to the cake
            // For now, we'll just update the status
            statusLabel.setText(toppingType + " added! Continue decorating or finish.");
            
            updateFinishButtonState();
        }
        
        private void updateFinishButtonState() {
            // Enable finish button once icing is applied
            finishButton.setDisable(appliedIcing == null);
        }
        
        private void finishDecorating() {
            // Create the completed cake with all decorations
            Cake decoratedCake = new Cake(
            );
            
            // Send completed cake to game controller
            gameController.setDecoratedCake(decoratedCake);
            
            // Move to next station
            gameController.moveToNextStation();
        }
        
        public Pane getView() {
            return view;
        }
        
        public void refresh() {
            // Reset decoration state
            appliedIcing = null;
            appliedBorderTip = null;
            hasDrip = false;
            hasStrawberries = false;
            hasCookies = false;
            hasSprinkles = false;
            
            // Reset UI elements
            cakeImageView.setImage(new Image("images/plain_cake.png"));
            
            whiteIcingImageView.setOpacity(1.0);
            whiteIcingImageView.setDisable(false);
            pinkIcingImageView.setOpacity(1.0);
            pinkIcingImageView.setDisable(false);
            chocolateIcingImageView.setOpacity(1.0);
            chocolateIcingImageView.setDisable(false);
            
            strawberriesImageView.setOpacity(1.0);
            strawberriesImageView.setDisable(false);
            cookiesImageView.setOpacity(1.0);
            cookiesImageView.setDisable(false);
            sprinklesImageView.setOpacity(1.0);
            sprinklesImageView.setDisable(false);
            dripImageView.setOpacity(1.0);
            dripImageView.setDisable(false);
            
            roundTipButton.setStyle("");
            starTipButton.setStyle("");
            
            finishButton.setDisable(true);
            statusLabel.setText("Drag icing onto the cake to start decorating");
        }
    }

    /**
 * Boxing Station Controller with drag-and-drop support
 */
private class BoxingStationController {
    private Pane view;
    private ImageView cakeImageView;
    private ImageView boxImageView;
    private ImageView ribbonRedImageView;
    private ImageView ribbonBlueImageView;
    private ImageView ribbonGoldImageView;
    private ImageView cardImageView;
    private Button finishButton;
    private Label statusLabel;
    
    // Tracking boxing state
    private boolean isCakeBoxed = false;
    private RibbonColor selectedRibbon = null;
    private boolean hasCard = false;
    
    public void initialize(GameController gameController) {
        view = new Pane();
        view.setPrefSize(600, 400);
        view.setStyle("-fx-background-color: #f0f0f0;");
        
        // Create cake visualization (draggable)
        cakeImageView = createDraggableImageView("images/decorated_cake.png", "cake", 150, 100, 150, 120);
        
        // Create box (drop target)
        boxImageView = new ImageView(new Image("images/cake_box.png")); // Replace with actual image
        boxImageView.setLayoutX(350);
        boxImageView.setLayoutY(150);
        boxImageView.setFitWidth(200);
        boxImageView.setFitHeight(150);
        
        // Create ribbons (draggable)
        ribbonRedImageView = createDraggableImageView("images/ribbon_red.png", "ribbon_red", 50, 250, 80, 40);
        ribbonBlueImageView = createDraggableImageView("images/ribbon_blue.png", "ribbon_blue", 150, 250, 80, 40);
        ribbonGoldImageView = createDraggableImageView("images/ribbon_gold.png", "ribbon_gold", 250, 250, 80, 40);
        
        // Create card (draggable)
        cardImageView = createDraggableImageView("images/card.png", "card", 350, 250, 60, 40);
        
        // Finish button
        finishButton = new Button("Finish Boxing");
        finishButton.setLayoutX(225);
        finishButton.setLayoutY(320);
        finishButton.setPrefSize(150, 40);
        finishButton.setDisable(true); // Disabled until cake is boxed
        
        statusLabel = new Label("Drag the cake into the box");
        statusLabel.setLayoutX(225);
        statusLabel.setLayoutY(20);
        
        // Setup drop handling for the box
        setupBoxDropHandling();
        
        // Add event handler for finish button
        finishButton.setOnAction(e -> {
            finishBoxing();
            SoundManager.getInstance().playSound("complete");
        });
        
        // Add elements to view
        view.getChildren().addAll(
            cakeImageView,
            boxImageView,
            ribbonRedImageView,
            ribbonBlueImageView,
            ribbonGoldImageView,
            cardImageView,
            finishButton,
            statusLabel
        );
    }
    
    private void setupBoxDropHandling() {
        // Handle drag over
        boxImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != boxImageView && 
                event.getDragboard().hasString()) {
                
                String draggedId = event.getDragboard().getString();
                
                // Allow cake if not boxed yet
                if (draggedId.equals("cake") && !isCakeBoxed) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                // Allow ribbon if cake is boxed but no ribbon applied yet
                else if (draggedId.startsWith("ribbon_") && isCakeBoxed && selectedRibbon == null) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                // Allow card if cake is boxed
                else if (draggedId.equals("card") && isCakeBoxed && !hasCard) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            }
            event.consume();
        });
        
        // Handle drop
        boxImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            
            if (db.hasString()) {
                String itemId = db.getString();
                
                if (itemId.equals("cake") && !isCakeBoxed) {
                    boxCake();
                    success = true;
                }
                else if (itemId.startsWith("ribbon_") && isCakeBoxed && selectedRibbon == null) {
                    applyRibbon(itemId);
                    success = true;
                }
                else if (itemId.equals("card") && isCakeBoxed && !hasCard) {
                    addCard();
                    success = true;
                }
            }
            
            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    private void boxCake() {
        isCakeBoxed = true;
        
        // Animate cake going into box
        TranslateTransition tt = new TranslateTransition(Duration.millis(800), cakeImageView);
        tt.setToX(boxImageView.getLayoutX() - cakeImageView.getLayoutX() + 25);
        tt.setToY(boxImageView.getLayoutY() - cakeImageView.getLayoutY() + 15);
        
        tt.setOnFinished(e -> {
            // Change box appearance to show it has cake
            boxImageView.setImage(new Image("images/cake_box_filled.png")); // Replace with actual image
            
            // Hide original cake
            cakeImageView.setVisible(false);
            
            statusLabel.setText("Add ribbon and card to decorate the box");
            updateFinishButtonState();
            
            SoundManager.getInstance().playSound("box_close");
        });
        
        tt.play();
    }
    
    private void applyRibbon(String ribbonId) {
        switch (ribbonId) {
            case "ribbon_red":
                selectedRibbon = RibbonColor.RED;
                ribbonRedImageView.setOpacity(0.5);
                ribbonRedImageView.setDisable(true);
                break;
            case "ribbon_blue":
                selectedRibbon = RibbonColor.BLUE;
                ribbonBlueImageView.setOpacity(0.5);
                ribbonBlueImageView.setDisable(true);
                break;
            case "ribbon_gold":
                selectedRibbon = RibbonColor.GOLD;
                ribbonGoldImageView.setOpacity(0.5);
                ribbonGoldImageView.setDisable(true);
                break;
        }
        
        // Update box image with ribbon
        boxImageView.setImage(new Image("images/cake_box_" + selectedRibbon.toString().toLowerCase() + ".png"));
        
        gameController.setRibbonColor(selectedRibbon);
        statusLabel.setText("Ribbon added! Add a card or finish boxing.");
        SoundManager.getInstance().playSound("ribbon");
        
        updateFinishButtonState();
    }
    
    private void addCard() {
        hasCard = true;
        
        // Animate card going onto box
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), cardImageView);
        tt.setToX(boxImageView.getLayoutX() - cardImageView.getLayoutX() + 130);
        tt.setToY(boxImageView.getLayoutY() - cardImageView.getLayoutY() + 60);
        
        tt.setOnFinished(e -> {
            // Update box image with card
            if (selectedRibbon != null) {
                boxImageView.setImage(new Image("images/cake_box_" + selectedRibbon.toString().toLowerCase() + "_card.png"));
            } else {
                boxImageView.setImage(new Image("images/cake_box_card.png"));
            }
            
            // Hide original card
            cardImageView.setVisible(false);
            
            gameController.addCard();
            statusLabel.setText("Card added! You can now finish boxing.");
            SoundManager.getInstance().playSound("card_place");
            
            updateFinishButtonState();
        });
        
        tt.play();
    }
    
    private void updateFinishButtonState() {
        // Enable finish button once cake is boxed
        finishButton.setDisable(!isCakeBoxed);
    }
    
    private void finishBoxing() {
        // Create boxed cake with all decorations
        gameController.setBoxingComplete(isCakeBoxed, selectedRibbon, hasCard);
        
        // Move to next station
        gameController.moveToNextStation();
    }
    
    public Pane getView() {
        return view;
    }
    
    public void refresh() {
        // Reset boxing state
        isCakeBoxed = false;
        selectedRibbon = null;
        hasCard = false;
        
        // Reset UI elements
        cakeImageView.setTranslateX(0);
        cakeImageView.setTranslateY(0);
        cakeImageView.setVisible(true);
        
        // Update cake image to match the decorated cake from previous station
        Cake cake = gameController.getDecoratedCake();
        if (cake != null) {
            // In a real implementation, we would set the image based on the cake's properties
            cakeImageView.setImage(new Image("images/decorated_cake.png"));
        }
        
        boxImageView.setImage(new Image("images/cake_box.png"));
        
        ribbonRedImageView.setOpacity(1.0);
        ribbonRedImageView.setDisable(false);
        ribbonBlueImageView.setOpacity(1.0);
        ribbonBlueImageView.setDisable(false);
        ribbonGoldImageView.setOpacity(1.0);
        ribbonGoldImageView.setDisable(false);
        
        cardImageView.setTranslateX(0);
        cardImageView.setTranslateY(0);
        cardImageView.setVisible(true);
        cardImageView.setOpacity(1.0);
        cardImageView.setDisable(false);
        
        finishButton.setDisable(true);
        statusLabel.setText("Drag the cake into the box");
    }
}

/**
 * Customer Station Controller for customer order handling
 */
private class CustomerStationController {
    private Pane view;
    private ImageView customerImageView;
    private ImageView thoughtBubbleImageView;
    private ImageView boxedCakeImageView;
    private ImageView counterImageView;
    private Label orderDetailsLabel;
    private Label feedbackLabel;
    private Button serveButton;
    private Button newCustomerButton;
    
    // Customer state
    private Customer currentCustomer;
    private boolean isServed = false;
    private int satisfactionScore = 0;
    
    public void initialize(GameController gameController) {
        view = new Pane();
        view.setPrefSize(600, 400);
        view.setStyle("-fx-background-color: #f0f0f0;");
        
        // Create customer and thought bubble
        customerImageView = new ImageView(new Image("images/customer.png")); // Replace with actual image
        customerImageView.setLayoutX(400);
        customerImageView.setLayoutY(100);
        customerImageView.setFitWidth(150);
        customerImageView.setFitHeight(200);
        
        thoughtBubbleImageView = new ImageView(new Image("images/thought_bubble.png"));
        thoughtBubbleImageView.setLayoutX(300);
        thoughtBubbleImageView.setLayoutY(50);
        thoughtBubbleImageView.setFitWidth(120);
        thoughtBubbleImageView.setFitHeight(100);
        
        // Create counter
        counterImageView = new ImageView(new Image("images/counter.png"));
        counterImageView.setLayoutX(150);
        counterImageView.setLayoutY(200);
        counterImageView.setFitWidth(300);
        counterImageView.setFitHeight(100);
        
        // Create boxed cake (draggable)
        boxedCakeImageView = createDraggableImageView("images/boxed_cake.png", "boxed_cake", 100, 150, 100, 100);
        
        // Order details and feedback labels
        orderDetailsLabel = new Label("Customer wants: ");
        orderDetailsLabel.setLayoutX(50);
        orderDetailsLabel.setLayoutY(50);
        orderDetailsLabel.setWrapText(true);
        orderDetailsLabel.setPrefWidth(200);
        
        feedbackLabel = new Label("");
        feedbackLabel.setLayoutX(200);
        feedbackLabel.setLayoutY(300);
        feedbackLabel.setWrapText(true);
        feedbackLabel.setPrefWidth(200);
        
        // Buttons
        serveButton = new Button("Serve Customer");
        serveButton.setLayoutX(100);
        serveButton.setLayoutY(320);
        serveButton.setPrefSize(150, 40);
        
        newCustomerButton = new Button("Next Customer");
        newCustomerButton.setLayoutX(350);
        newCustomerButton.setLayoutY(320);
        newCustomerButton.setPrefSize(150, 40);
        newCustomerButton.setVisible(false);
        
        // Set up the customer area as a drop target
        setupCustomerDropTarget();
        
        // Add event handlers for buttons
        serveButton.setOnAction(e -> {
            serveCake();
            SoundManager.getInstance().playSound("serve");
        });
        
        newCustomerButton.setOnAction(e -> {
            nextCustomer();
            SoundManager.getInstance().playSound("next_customer");
        });
        
        // Add elements to view
        view.getChildren().addAll(
            counterImageView,
            boxedCakeImageView,
            customerImageView,
            thoughtBubbleImageView,
            orderDetailsLabel,
            feedbackLabel,
            serveButton,
            newCustomerButton
        );
    }
    
    private void setupCustomerDropTarget() {
        // Handle drag over
        customerImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != customerImageView && 
                event.getDragboard().hasString() &&
                !isServed) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        
        // Handle drop
        customerImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            
            if (db.hasString() && db.getString().equals("boxed_cake") && !isServed) {
                serveCakeViaDrag();
                success = true;
            }
            
            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    public void setCustomer(Customer customer) {
        currentCustomer = customer;
        
        // Update UI with customer preferences
        StringBuilder orderText = new StringBuilder("Customer wants:\n");
        orderText.append("- ").append(customer.getPreferredFlavor().toString()).append(" cake\n");
        orderText.append("- ").append(customer.getPreferredIcing().toString()).append(" icing\n");
        
        if (customer.wantsTopping()) {
            orderText.append("- With toppings\n");
        }
        
        if (customer.getPreferredRibbon() != null) {
            orderText.append("- ").append(customer.getPreferredRibbon().toString()).append(" ribbon\n");
        }
        
        if (customer.wantsCard()) {
            orderText.append("- With a card\n");
        }
        
        orderDetailsLabel.setText(orderText.toString());
        
        // Update thought bubble with preference image
        String thoughtImagePath = "images/" + customer.getPreferredFlavor().toString().toLowerCase() + 
                                "_" + customer.getPreferredIcing().toString().toLowerCase() + "_cake.png";
        Image thoughtImage = new Image(thoughtImagePath);
        
        // Create a small image view inside the thought bubble
        ImageView thoughtCakeImageView = new ImageView(thoughtImage);
        thoughtCakeImageView.setFitWidth(60);
        thoughtCakeImageView.setFitHeight(60);
        
        // Update the UI
        isServed = false;
        feedbackLabel.setText("");
        serveButton.setVisible(true);
        newCustomerButton.setVisible(false);
    }
    
    private void serveCakeViaDrag() {
        // Animate cake going to customer
        TranslateTransition tt = new TranslateTransition(Duration.millis(800), boxedCakeImageView);
        tt.setToX(customerImageView.getLayoutX() - boxedCakeImageView.getLayoutX() - 50);
        tt.setToY(customerImageView.getLayoutY() - boxedCakeImageView.getLayoutY() + 50);
        
        tt.setOnFinished(e -> {
            isServed = true;
            evaluateCustomerSatisfaction();
        });
        
        tt.play();
    }
    
    private void serveCake() {
        serveCakeViaDrag();
    }
    
    private void evaluateCustomerSatisfaction() {
        if (currentCustomer == null) return;
        
        // Get the cake that was created
        Cake cake = gameController.getDecoratedCake();
        RibbonColor ribbonColor = gameController.getRibbonColor();
        boolean hasCard = gameController.hasCard();
        
        // Calculate satisfaction score
        satisfactionScore = 0;
        
        // Check flavor match
        if (cake.getFlavor() == currentCustomer.getPreferredFlavor()) {
            satisfactionScore += 25;
        }
        
        // Check icing match
        if (cake.getIcingType() == currentCustomer.getPreferredIcing()) {
            satisfactionScore += 25;
        }
        
        // Check toppings
        if (currentCustomer.wantsTopping() && 
            (cake.hasStrawberries() || cake.hasCookies() || cake.hasSprinkles())) {
            satisfactionScore += 15;
        }
        
        // Check ribbon match
        if (currentCustomer.getPreferredRibbon() == ribbonColor) {
            satisfactionScore += 20;
        }
        
        // Check card
        if (currentCustomer.wantsCard() == hasCard) {
            satisfactionScore += 15;
        }
        
        // Update UI based on satisfaction
        String feedbackText;
        if (satisfactionScore >= 90) {
            feedbackText = "Perfect! The customer loves it!";
            customerImageView.setImage(new Image("images/customer_happy.png"));
            SoundManager.getInstance().playSound("customer_happy");
        } else if (satisfactionScore >= 70) {
            feedbackText = "Good job! The customer is satisfied.";
            customerImageView.setImage(new Image("images/customer_satisfied.png"));
            SoundManager.getInstance().playSound("customer_satisfied");
        } else if (satisfactionScore >= 50) {
            feedbackText = "Not bad. The customer is okay with it.";
            customerImageView.setImage(new Image("images/customer_neutral.png"));
            SoundManager.getInstance().playSound("customer_neutral");
        } else {
            feedbackText = "The customer isn't happy with the cake.";
            customerImageView.setImage(new Image("images/customer_unhappy.png"));
            SoundManager.getInstance().playSound("customer_unhappy");
        }
        
        feedbackLabel.setText(feedbackText);
        
        // Add score to game
        gameController.addCustomerScore(satisfactionScore);
        
        // Show next customer button
        serveButton.setVisible(false);
        newCustomerButton.setVisible(true);
    }
    
    private void nextCustomer() {
        // Reset the station for a new customer
        boxedCakeImageView.setTranslateX(0);
        boxedCakeImageView.setTranslateY(0);
        feedbackLabel.setText("");
        
        // Generate a new customer
        gameController.generateNewCustomer();
    }
    
    public Pane getView() {
        return view;
    }
    
    public void refresh() {
        // Reset customer state
        isServed = false;
        satisfactionScore = 0;
        
        // Reset UI elements
        boxedCakeImageView.setTranslateX(0);
        boxedCakeImageView.setTranslateY(0);
        
        // Update boxed cake image based on the current cake
        // In a real implementation, we would set the image based on the cake's properties
        boxedCakeImageView.setImage(new Image("images/boxed_cake.png"));
        
        // Reset customer image
        customerImageView.setImage(new Image("images/customer.png"));
        
        // Set new customer
        currentCustomer = gameController.getCurrentCustomer();
        if (currentCustomer != null) {
            setCustomer(currentCustomer);
        }
        
        feedbackLabel.setText("");
        serveButton.setVisible(true);
        newCustomerButton.setVisible(false);
    }
}

@Override
public void onGameStateChanged(com.cakegame.enums.GameState newState) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onGameStateChanged'");
}
}