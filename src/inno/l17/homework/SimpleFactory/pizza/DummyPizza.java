package inno.l17.homework.SimpleFactory.pizza;

import java.util.concurrent.ThreadLocalRandom;

public class DummyPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("Prepare yourself for dummy pizza appearance!");
    }

    @Override
    public void bake() {
        System.out.println("It will cut u on " +
                ThreadLocalRandom.current().nextInt(10000000) + " parts!!!");
    }

    @Override
    public void cut() {
        System.out.println("HERE'S JOHNNY!!!\n\n" +
                "▒▒▒▒▒─▄▄▄▄▄▄▄▄▄▄▄▄▄▄─▒▒▒\n" +
                "▒▒█▒▒─████DUMMY█████─▒▒▒\n" +
                "▒█▐▒▒─██████████████─▒▒\n" +
                "▒▌▐▒▒██▄▀██████▀▄███─▒▒\n" +
                "▐┼▐▒▒██▄▄▄▄██▄▄▄▄███─▒▒▒\n" +
                "▐┼▐▒▒███████████████─▒▒▒\n" +
                "▐▄▐████─▀▐▐▀█─█─▌▐██▄▒\n" +
                "▒▒█████──────────▐███▌\n" +
                "▒▒█▀▀██▄█─▄───▐─▄███▀▒\n" +
                "▒▒█▒▒▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▒\n" +
                "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
    }

    @Override
    public void box() {
        System.out.println("Dummy pizza puts your well-baked and well-cut body into the box.\n" +
                "DING-DONG!!! Your order completed, Human!!!");
    }
}
