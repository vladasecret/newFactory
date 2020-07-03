package factory;

public class CarBuilderController implements Observer {
    private final CarBuilder carFactory;

    public CarBuilderController(CarBuilder carFactory) {
        this.carFactory = carFactory;
        carFactory.addCarStorageObserver(this);
    }

    public void start(){
        int startNumTasks = carFactory.getCarStorage().getCapacity();
        for (int i = 0; i < startNumTasks; i++) {
            carFactory.buildCar();
        }
    }

    @Override
    public void update() {
        carFactory.buildCar();
    }

    public void stop() throws InterruptedException {
        carFactory.stop();
    }
}