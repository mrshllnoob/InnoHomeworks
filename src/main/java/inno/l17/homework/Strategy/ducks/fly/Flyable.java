package inno.l17.homework.Strategy.ducks.fly;

public class Flyable implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I'm flying!!!");
    }
}
