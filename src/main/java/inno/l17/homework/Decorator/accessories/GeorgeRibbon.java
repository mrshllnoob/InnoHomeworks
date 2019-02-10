package inno.l17.homework.Decorator.accessories;

import inno.l17.homework.Decorator.Cloth;
import inno.l17.homework.Decorator.Decorator;

public class GeorgeRibbon extends Decorator {

    public GeorgeRibbon(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public void getDescription() {
        System.out.println("Nice accessory from mother Russia. " +
                                "For people who loves parades.");
    }

    @Override
    public int getCost() {
        System.out.println("George ribbon is priceless for sure.");
        return 0 + cloth.getCost();
    }
}
