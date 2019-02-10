package inno.l17.homework.Decorator.clothes;

import inno.l17.homework.Decorator.Cloth;

public class BlueTShirt implements Cloth {

    @Override
    public void getDescription() {
        System.out.println("Nice blue t-shirt. Looks cool with everything!!!");
    }

    @Override
    public int getCost() {
        System.out.println("Blue t-shirt cost is 10 bucks");
        return 10;
    }
}
