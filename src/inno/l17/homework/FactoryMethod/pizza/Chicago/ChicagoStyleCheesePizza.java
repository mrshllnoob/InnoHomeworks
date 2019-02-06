package inno.l17.homework.FactoryMethod.pizza.Chicago;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Cheese Pizza";
        dough = "Extra thick crust dough";
        sauce = "Tomato sauce";

        toppings.add("Shredded Mozzarella");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }

}
