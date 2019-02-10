package inno.l17.homework.Strategy;

import inno.l17.homework.Strategy.ducks.Ducky;
import inno.l17.homework.Strategy.ducks.RedHeadDuck;
import inno.l17.homework.Strategy.ducks.RubberDucky;

public class DuckiesTestDrive {

    public static void main(String[] args) {
        Ducky ducky1 = new RedHeadDuck();
        ducky1.display();
        System.out.println();
        Ducky ducky2 = new RubberDucky();
        ducky2.display();
    }

}
