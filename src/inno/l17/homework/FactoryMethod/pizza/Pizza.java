package inno.l17.homework.FactoryMethod.pizza;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Pizza {

    public String name;
    protected String dough;
    protected String sauce;
    protected ArrayList toppings = new ArrayList();

    public void prepare() {
        System.out.println("Preparing " + getName());
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (int i=0; i<toppings.size(); i++)
            System.out.println(" " + toppings.get(i));
    }

    public void bake() {
        System.out.println("Bake for "+ ThreadLocalRandom.current().nextInt(30) + " minutes");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
