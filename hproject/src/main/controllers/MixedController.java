public class MixedController {
    private Batter batter;

    public void startNewMix() {
        batter = new Batter();
    }

    public void addIngredient(Ingredient ingredient) {
        if (batter == null) startNewMix();
        batter.addIngredient(ingredient);
    }

    public Batter getBatter() {
        return batter.isReady() ? batter : null;
    }
}

