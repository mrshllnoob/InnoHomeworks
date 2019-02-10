package inno.l17.homework.Decorator.accessories;

import inno.l17.homework.Decorator.Cloth;
import inno.l17.homework.Decorator.Decorator;

public class Spurs extends Decorator {

    public Spurs(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public void getDescription() {
        System.out.println("Spurs will show who's real alpha-male right here.");
    }

    @Override
    public int getCost() {
        System.out.println("Spurs? That manly accessory will cost you only 100 bucks");
        return 100 + cloth.getCost();
    }
}
