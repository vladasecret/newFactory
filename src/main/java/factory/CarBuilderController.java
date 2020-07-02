package factory;

public class CarBuilderController implements Runnable {
    private final CarBuilder carFactory;

    public CarBuilderController(CarBuilder carFactory) {
        this.carFactory = carFactory;
    }

    public void run() {
        for (int i = 0; i < carFactory.getCarStorage().getCapacity(); i++) {
            carFactory.buildCar();
        }

        while (!Thread.currentThread().isInterrupted()) {
            synchronized(carFactory.getCarStorage()) {
                try {
                    carFactory.getCarStorage().wait();
                } catch (InterruptedException e) {
                    return;
                }
                carFactory.buildCar();
            }
        }
    }
    public void stop() throws InterruptedException {
        carFactory.stop();
    }
}