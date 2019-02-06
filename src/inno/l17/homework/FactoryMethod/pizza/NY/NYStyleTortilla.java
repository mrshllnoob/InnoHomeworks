package inno.l17.homework.FactoryMethod.pizza.NY;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

import java.util.concurrent.ThreadLocalRandom;

public class NYStyleTortilla extends Pizza {
    public NYStyleTortilla(){
        name = "Tortilla";
        dough = "Tortilla";
        sauce = "Another Tortilla";

        int toppingsAmount = ThreadLocalRandom.current().nextInt(100);
        for (int i=0;i<toppingsAmount;i++)
            toppings.add("Tortilla???");
    }
}
