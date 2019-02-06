package inno.l17.homework.FactoryMethod.pizza.Chicago;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class ChicagoStyleVeggiePizza extends Pizza {

    public ChicagoStyleVeggiePizza() {
        name = "Chicago Veggie";
        dough = "Thick layer of grass";
        sauce = "Tasty Grass Sauce";

        toppings.add("Hate");
        toppings.add("Pain");
        toppings.add("Grass");
    }

    @Override
    public void cut() {
        System.out.println("Dont need to cut");
    }
}
