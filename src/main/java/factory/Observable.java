package factory;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    public synchronized void addObserver(Observer o) {
        if (o != null && !observers.contains(o))
            observers.add(o);
    }

    public synchronized void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    public synchronized void changed() {
        notifyObservers();
    }

    private void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
