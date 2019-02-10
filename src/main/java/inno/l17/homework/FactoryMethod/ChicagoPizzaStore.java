package inno.l17.homework.FactoryMethod;

import inno.l17.homework.FactoryMethod.pizza.Chicago.ChicagoStyleCheesePizza;
import inno.l17.homework.FactoryMethod.pizza.Chicago.ChicagoStylePoppadum;
import inno.l17.homework.FactoryMethod.pizza.Chicago.ChicagoStyleVeggiePizza;
import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        switch(type) {
            case "cheese":
                return new ChicagoStyleCheesePizza();
            case "veggie":
                return new ChicagoStyleVeggiePizza();
            default:
                return new ChicagoStylePoppadum();
        }
    }
}
