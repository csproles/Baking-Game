public class OvenController {
    public Cake bakeCake(Batter batter, Shape shape) {
        if (!batter.isReady()) return null;
        Cake cake = new Cake(batter.getFlavor(), shape);
        cake.bake();
        return cake;
    }
}

