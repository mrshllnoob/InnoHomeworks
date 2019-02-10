package inno.l17.homework.Decorator.clothes;

import inno.l17.homework.Decorator.Cloth;

public class WhiteCowboyHat implements Cloth {

    @Override
    public void getDescription() {
        System.out.println("White cowboy hat. Mediocre accessory." +
                " If u are a cowboy.");
    }

    @Override
    public int getCost() {
        System.out.println("100 bucks, good cost for a wealthy person.");
        return 100;
    }
}
