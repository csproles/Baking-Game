public class Batter {
    private boolean hasMix;
    private boolean hasEgg;
    private boolean hasMilk;
    private Flavor flavor;

    public void addIngredient(Ingredient ingredient) {
        switch (ingredient.getType()) {
            case MIX: hasMix = true; break;
            case EGG: hasEgg = true; break;
            case MILK: hasMilk = true; break;
            case FLAVOR: flavor = Flavor.valueOf(ingredient.getName().toUpperCase()); break;
        }
    }

    public boolean isReady() {
        return hasMix && hasEgg && hasMilk && flavor != null;
    }

    // Getters for transferring to cake tin
}

