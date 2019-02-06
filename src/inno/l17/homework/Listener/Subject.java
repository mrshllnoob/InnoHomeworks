package inno.l17.homework.Listener;

public interface Subject {
    void registerObs(Observer obs);
    void notifyObs();
    void removeObs(Observer obs);
}
