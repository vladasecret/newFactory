package factory;

import carParts.CarPart;

public class Supplier<T extends CarPart> implements Runnable {
    private final Storage<T> storage;
    private Integer period;

    private final Class<T> partType;

    public Supplier(Storage<T> s, Integer p, Class<T> type) {
        storage = s;
        period = p;
        partType = type;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                T detail = partType.getDeclaredConstructor().newInstance();
                storage.put(detail);
                Thread.sleep(period);
            } catch (InterruptedException e){
                break;
            }
            catch (Exception e){
            }
        }
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}

