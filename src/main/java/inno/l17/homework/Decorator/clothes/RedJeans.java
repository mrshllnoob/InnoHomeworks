package inno.l17.homework.Decorator.clothes;

import inno.l17.homework.Decorator.Cloth;

public class RedJeans implements Cloth {

    @Override
    public void getDescription() {
        System.out.println("Great red jeans. Looks strange with everything." +
                "Only for brave persons.");
    }

    @Override
    public int getCost() {
        System.out.println("Beautiful red jeans costs 10 bucks");
        return 10;
    }
}
