package inno.l17.homework.FactoryMethod;

import inno.l17.homework.FactoryMethod.pizza.NY.NYStyleCheesePizza;
import inno.l17.homework.FactoryMethod.pizza.NY.NYStyleTortilla;
import inno.l17.homework.FactoryMethod.pizza.NY.NYStyleVeggiePizza;
import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        switch(type) {
            case "cheese":
                return new NYStyleCheesePizza();
            case "veggie":
                return new NYStyleVeggiePizza();
            default:
                return new NYStyleTortilla();
        }
    }
}
