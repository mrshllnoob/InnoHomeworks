package inno.l17.homework.Strategy.ducks;

import inno.l17.homework.Strategy.ducks.fly.UnableToFly;
import inno.l17.homework.Strategy.ducks.quack.VeryNoisyQuackable;

public class RubberDucky extends Ducky{

    public RubberDucky() {
        this.name = "Rubber Ducky";
        this.flyBehaviour = new UnableToFly();
        this.quackBehaviour = new VeryNoisyQuackable();
    }

}
