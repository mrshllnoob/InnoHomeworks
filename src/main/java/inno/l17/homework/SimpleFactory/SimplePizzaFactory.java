package inno.l17.homework.SimpleFactory;

import inno.l17.homework.SimpleFactory.pizza.*;

public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        switch (type) {
            case "cheese":
                return new CheesePizza();
            case "veggie":
                return new VeggiePizza();
            case "pepperoni":
                return new PepperoniPizza();
            default:
                return new DummyPizza();
        }
    }
}
