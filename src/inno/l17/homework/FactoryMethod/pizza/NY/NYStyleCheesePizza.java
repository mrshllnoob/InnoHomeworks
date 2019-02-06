package inno.l17.homework.FactoryMethod.pizza.NY;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza(){
        name = "NY style cheese pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Regiano Cheese");
    }

}
