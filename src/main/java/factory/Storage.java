package factory;

import java.util.ArrayList;

public class Storage<T> extends Observable {
    private final ArrayList<T> units;
    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        units = new ArrayList<>();
    }

    public synchronized void put(T detail) throws InterruptedException {
        while (units.size() == capacity) {
            wait();
        }
        units.add(detail);
        changed();
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (units.size() == 0) {
            wait();
        }
        T detail = units.remove(units.size() - 1);
        changed();
        notifyAll();
        return detail;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer currentCountElements() {
        return units.size();
    }
}
