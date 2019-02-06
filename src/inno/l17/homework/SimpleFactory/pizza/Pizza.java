package inno.l17.homework.SimpleFactory.pizza;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Pizza {
    public void prepare() {
        System.out.println("Preparing your pizza:");
    }

    public void bake() {
        System.out.println("Baking for " +
                    ThreadLocalRandom.current().nextInt(10000000) + " mins!");
    }

    public void cut() {
        System.out.println("Trying to cut your baked pizza on " +
                ThreadLocalRandom.current().nextInt(10000000) + " parts!");
    }

    public void box() {
        System.out.println("Brought well-cut pizza to the box!");
    }
}
