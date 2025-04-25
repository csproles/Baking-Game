package controllers;

import models.*;
import logic.CakeLogic;
import java.util.*;

public class MainController {
    private MixedController mixedController;
    private OvenController ovenController;
    private CakeLogic cakeLogic;
    private Box box;

    private Batter currentBatter;
    private Cake currentCake;

    public MainController() {
        mixedController = new MixedController();
        ovenController = new OvenController();
        cakeLogic = new CakeLogic(); // You may make this static if no instance state is needed
        box = new Box();
    }

    // 1. Ingredient added by player
    public void addIngredientToMixer(Ingredient ingredient) {
        mixedController.addIngredient(ingredient);
    }

    // 2. Trigger mixing
    public boolean mixBatter() {
        currentBatter = mixedController.getBatter();
        return currentBatter != null;
    }

    // 3. Send to oven
    public boolean bakeCake(Shape shape) {
        if (currentBatter == null) return false;
        currentCake = ovenController.bakeCake(currentBatter, shape);
        return currentCake != null;
    }

    // 4. Decoration
    public boolean decorateCake(Icing icing, Border border, List<Topping> toppings, boolean drip) {
        if (currentCake == null || !currentCake.isBaked()) return false;
        currentCake = cakeLogic.decorateCake(currentCake, icing, border, toppings, drip);
        return currentCake != null && currentCake.isDecorated();
    }

    // 5. Boxing
    public void boxCake() {
        box.placeCake(currentCake);
    }

    // 6. Deliver
    public void deliverCake() {
        box.deliverToCustomer();
        reset(); // Clear for new cake
    }

    private void reset() {
        mixedController.startNewMix();
        currentBatter = null;
        currentCake = null;
        box = new Box();
    }
}

package logic;

import models.*;

import java.util.List;

public class CakeLogic {
    public Cake decorateCake(Cake cake, Icing icing, Border border, List<Topping> toppings, boolean drip) {
        if (!cake.isBaked()) return null;

        cake.decorate(icing, border, toppings, drip);
        return cake;
    }
}

