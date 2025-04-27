package controller;

import models.Cake;
import models.enums.Border;
import models.enums.Icing;
import models.enums.Topping;

import java.util.List;

public class CakeLogic {

    public static void decorateCake(Cake cake, Icing icing, Border border, List<Topping> toppings, boolean drip) {
        if (cake == null) {
            System.out.println("Cannot decorate a null cake.");
            return;
        }

        cake.setIcing(icing);
        cake.setBorder(border);
        cake.setToppings(toppings);
        cake.setDrip(drip);

        cake.setDecorated(true);  // You need to have a boolean field decorated in Cake model
    }
}

