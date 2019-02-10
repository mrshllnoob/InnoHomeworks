package inno.l17.homework.FactoryMethod.pizza.Chicago;

import inno.l17.homework.FactoryMethod.pizza.Pizza;

public class ChicagoStylePoppadum extends Pizza {
    @Override
    public void prepare() {
        System.out.println("Prepare Poppadum");
    }

    @Override
    public void bake() {
        System.out.println("Bake Poppadum");
    }

    @Override
    public void cut() {
        System.out.println("Cut Poppadum?");
    }

    @Override
    public void box() {
        System.out.println("Box Poppadum");
    }
}
