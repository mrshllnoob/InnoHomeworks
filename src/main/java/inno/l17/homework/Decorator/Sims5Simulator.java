package inno.l17.homework.Decorator;

import inno.l17.homework.Decorator.accessories.GeorgeRibbon;
import inno.l17.homework.Decorator.accessories.Spurs;
import inno.l17.homework.Decorator.clothes.RedJeans;

public class Sims5Simulator {

    public static void main(String[] args) {
        Cloth cloth = new Spurs(new GeorgeRibbon(new RedJeans()));
        int clothCost = cloth.getCost();
        System.out.println("Your S80 set will cost u around " + clothCost);
    }

}
