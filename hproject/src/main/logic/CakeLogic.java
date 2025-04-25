public Cake decorateCake(Cake cake, Icing icing, Border border, List<Topping> toppings, boolean drip) {
    if (!cake.isBaked()) return null;
    cake.decorate(icing, border, toppings, drip);
    return cake;
}

