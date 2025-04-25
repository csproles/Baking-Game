public class Cake {
    private Flavor flavor;
    private Shape shape;
    private boolean isBaked;
    private boolean isDecorated;

    private Icing icing;
    private Border border;
    private List<Topping> toppings;
    private boolean drip;

    public Cake(Flavor flavor, Shape shape) {
        this.flavor = flavor;
        this.shape = shape;
        this.toppings = new ArrayList<>();
    }

    public void bake() {
        this.isBaked = true;
    }

    public void decorate(Icing icing, Border border, List<Topping> toppings, boolean drip) {
        this.icing = icing;
        this.border = border;
        this.toppings = toppings;
        this.drip = drip;
        this.isDecorated = true;
    }

    public boolean isComplete() {
        return isBaked && isDecorated;
    }

    // Getters and toString
}

