package inno.l17.homework.Listener.impls;

import inno.l17.homework.Listener.Observer;
import inno.l17.homework.Listener.Subject;

import java.util.ArrayList;
import java.util.List;

public class Girl implements Subject {

    private String state = "not interested in you";
    private List<Observer> spectators = new ArrayList<>();
    private String id;

    public Girl(String id) {
        this.id = id;
        System.out.println(id + " just came in here");
    }

    @Override
    public void registerObs(Observer obs) {
        spectators.add(obs);
        System.out.println(obs + " registered");
    }

    @Override
    public void notifyObs() {
        for (Observer obs : spectators)
            obs.update(this.state);
        System.out.println("All spectators are notified");
    }

    @Override
    public void removeObs(Observer obs) {
        spectators.remove(obs);
        System.out.println(obs + " removed");
    }

    private String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("Subject state changed!");
        notifyObs();
    }

}
