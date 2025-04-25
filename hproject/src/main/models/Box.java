package models;

public class Box {
    private Cake cake;

    public void placeCake(Cake cake) {
        if (cake.isComplete()) {
            this.cake = cake;
        }
    }

    public void deliverToCustomer() {
        if (cake != null) {
            System.out.println("Cake delivered: " + cake);
        }
    }
}


