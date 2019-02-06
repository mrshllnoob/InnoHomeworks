package inno.l17.homework.Listener.impls;

import inno.l17.homework.Listener.Observer;
import inno.l17.homework.Listener.Subject;

public class Dude implements Observer {

    private Subject grill;

    public Dude() { }

    public Dude(Subject subject) {
        this.grill = subject;
    }

    @Override
    public void update(String state) {
        System.out.println("Subject state is: " + state);
    }

    public void startSpectating(Subject subject) {
        this.setGrill(subject);
        subject.registerObs(this);
    }

    public void stopSpectating() {
        getGrill().removeObs(this);
    }

    public Subject getGrill() {
        return grill;
    }

    private void setGrill(Subject grill) {
        this.grill = grill;
    }
}
