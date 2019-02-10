package inno.l17.homework.SimpleFactory;

import inno.l17.homework.SimpleFactory.pizza.Pizza;

public class PizzaStore {

    private SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) throws InterruptedException {
        Pizza pizza = factory.createPizza(type);
        Thread.sleep(2000);
        pizza.prepare();
        Thread.sleep(2000);
        pizza.bake();
        Thread.sleep(2000);
        pizza.cut();
        Thread.sleep(2000);
        pizza.box();
        return pizza;
    }

}
