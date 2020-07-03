package factory;

import carParts.Accessory;
import carParts.Body;
import carParts.Car;
import carParts.Engine;
import threadPool.ThreadPool;

import java.util.Arrays;

public class CarBuilder {
    private final Storage<Engine> enginesStorage;
    private final Storage<Body> bodiesStorage;
    private final Storage<Accessory> accessoriesStorage;
    private final Storage<Car> carStorage;

    private final ThreadPool workers;

    private Integer workTime;
    private final WorkerStatus[] statuses;

    public CarBuilder(Integer noOfWorkers, Storage<Engine> enginesStorage, Storage<Body> bodiesStorage, Storage<Accessory> accessoriesStorage, Storage<Car> carStorage) {
        this.enginesStorage = enginesStorage;
        this.bodiesStorage = bodiesStorage;
        this.accessoriesStorage = accessoriesStorage;
        this.carStorage = carStorage;

        workTime = 3000;

        workers = new ThreadPool(noOfWorkers);

        statuses = new WorkerStatus[noOfWorkers];
        Arrays.fill(statuses, WorkerStatus.SLEEPING);
    }

    public void addCarStorageObserver(Observer o){
        carStorage.addObserver(o);
    }

    public void buildCar() {
        workers.addTask(new WorkerTask(this));
    }

    public Storage<Engine> getEnginesStorage() {
        return enginesStorage;
    }

    public Storage<Body> getBodiesStorage() {
        return bodiesStorage;
    }

    public Storage<Accessory> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer t) {
        workTime = t;
    }

    public WorkerStatus[] getStatuses() {
        return statuses;
    }

    public void setStatus(Integer i, WorkerStatus newStatus) {
        statuses[i] = newStatus;
    }

    public void stop() throws InterruptedException {
        workers.stop();
    }
}
