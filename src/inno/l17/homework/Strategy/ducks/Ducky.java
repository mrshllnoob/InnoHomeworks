package inno.l17.homework.Strategy.ducks;

import inno.l17.homework.Strategy.ducks.fly.FlyBehaviour;
import inno.l17.homework.Strategy.ducks.quack.QuackBehaviour;

public abstract class Ducky {

    String name;
    QuackBehaviour quackBehaviour;
    FlyBehaviour flyBehaviour;

    public void swim() {
        System.out.println(name + " swimming!!!");
    }

    public void display() {
        this.swim();
        this.performFly();
        this.performQuack();
    }

    public void performQuack() {
        this.quackBehaviour.quack();
    }

    public void performFly() {
        this.flyBehaviour.fly();
    }

}
