package inno.l17.homework.Strategy.ducks.quack;

public class Quackable implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Quack Quack performance");
    }
}
