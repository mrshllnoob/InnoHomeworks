package inno.l17.homework.Strategy.ducks.quack;

public class VeryNoisyQuackable implements inno.l17.homework.Strategy.ducks.quack.QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Performing most ugly quack-noise in your life");
    }
}
