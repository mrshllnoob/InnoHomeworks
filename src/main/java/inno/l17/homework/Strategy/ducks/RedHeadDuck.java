package inno.l17.homework.Strategy.ducks;

import inno.l17.homework.Strategy.ducks.fly.Flyable;
import inno.l17.homework.Strategy.ducks.quack.Quackable;

public class RedHeadDuck extends Ducky {

    public RedHeadDuck() {
        this.name = "Red head duck";
        this.flyBehaviour = new Flyable();
        this.quackBehaviour = new Quackable();
    }
}
