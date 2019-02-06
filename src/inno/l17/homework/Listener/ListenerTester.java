package inno.l17.homework.Listener;

import inno.l17.homework.Listener.impls.Dude;
import inno.l17.homework.Listener.impls.Girl;

public class ListenerTester {

    public static void main(String[] args) {
        Dude justDude = new Dude();
        Girl grill = new Girl("Ten out of ten girl");
        justDude.startSpectating(grill);
        grill.setState("still not watching u");
        grill.removeObs(justDude);
    }
}
