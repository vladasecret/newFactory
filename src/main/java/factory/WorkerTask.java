package factory;

import carParts.Accessory;
import carParts.Body;
import carParts.Car;
import carParts.Engine;
import threadPool.Task;

public class WorkerTask implements Task {
    private final CarBuilder carFactory;
    private Car car;

    public WorkerTask(CarBuilder f) {
        carFactory = f;
    }



    @Override
    public void performWork() throws InterruptedException {
        Integer workerNo = Integer.parseInt(Thread.currentThread().getName());
        carFactory.setStatus(workerNo, WorkerStatus.WAITING_FOR_DETAILS);

        Body b = carFactory.getBodiesStorage().get();
        Accessory a = carFactory.getAccessoriesStorage().get();
        Engine e = carFactory.getEnginesStorage().get();

        carFactory.setStatus(workerNo, WorkerStatus.WORKING);

        car = new Car(b, a, e);
        Thread.sleep(carFactory.getWorkTime());

        carFactory.setStatus(workerNo, WorkerStatus.WAITING_FOR_PLACE);
        carFactory.getCarStorage().put(car);
        carFactory.setStatus(workerNo, WorkerStatus.SLEEPING);
    }
}
