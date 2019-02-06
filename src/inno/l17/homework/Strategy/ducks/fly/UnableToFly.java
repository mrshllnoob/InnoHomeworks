package inno.l17.homework.Strategy.ducks.fly;

public class UnableToFly implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("Unfortunately, was born to swim at bathroom, not flying");
    }
}
