package inno.l17.homework.FactoryMethod;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

public abstract class PizzaStore {

    public final Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        try {
            pizza.prepare();
            Thread.sleep(2000);
            pizza.bake();
            Thread.sleep(2000);
            pizza.cut();
            pizza.box();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}
