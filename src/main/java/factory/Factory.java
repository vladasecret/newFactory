package factory;

import carParts.Accessory;
import carParts.Body;
import carParts.Car;
import carParts.Engine;
import main.FactoryConfig;

import java.util.ArrayList;

public class Factory {
    private final Integer __DEFAULT_SPEED = 3000;

    private final Storage<Accessory> accessoriesStorage;
    private final Storage<Engine> enginesStorage;
    private final Storage<Body> bodiesStorage;
    private final Storage<Car> carsStorage;

    private final ArrayList<Supplier<Accessory>> accessoriesSuppliers;
    private final Supplier<Engine> enginesSupplier;
    private final Supplier<Body> bodiesSupplier;

    private final ArrayList<Dealer> dealers;

    private final CarBuilder carBuilder;
    private final CarBuilderController controller;

    private final ArrayList<Thread> threads;

    public Factory(FactoryConfig factoryConfig) {
        accessoriesStorage = new Storage<>(factoryConfig.getAccessoriesStorageSize());
        enginesStorage = new Storage<>(factoryConfig.getEnginesStorageSize());
        bodiesStorage = new Storage<>(factoryConfig.getBodiesStorageSize());
        carsStorage = new Storage<>(factoryConfig.getCarsStorageSize());

        accessoriesSuppliers = new ArrayList<>();
        for (int i = 0; i < factoryConfig.getAccessorySuppliers(); i++) {
            accessoriesSuppliers.add(new Supplier<>(accessoriesStorage, __DEFAULT_SPEED, Accessory.class));
        }

        enginesSupplier = new Supplier<>(enginesStorage, __DEFAULT_SPEED, Engine.class);
        bodiesSupplier = new Supplier<>(bodiesStorage, __DEFAULT_SPEED, Body.class);

        carBuilder = new CarBuilder(factoryConfig.getWorkersCount(), enginesStorage, bodiesStorage, accessoriesStorage, carsStorage);
        controller = new CarBuilderController(carBuilder);

        dealers = new ArrayList<>();
        for (int i = 0; i < factoryConfig.getDealers(); i++) {
            dealers.add(new Dealer(carsStorage, __DEFAULT_SPEED, factoryConfig.doLogSales()));
        }

        threads = new ArrayList<>();
        threads.add(new Thread(enginesSupplier, "enginesSupplier"));
        threads.add(new Thread(bodiesSupplier, "bodiesSupplier"));

        for (var supplier : accessoriesSuppliers) {
            threads.add(new Thread(supplier, "accessoriesSuppliers"));
        }

        int count = 0;
        for (var dealer : dealers) {
            threads.add(new Thread(dealer, "dealer" + String.valueOf(count++)));
        }

        threads.add(new Thread(controller, "controller"){
            @Override
            public void interrupt() {
                try {
                    controller.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.interrupt();
            }
        });
    }

    public void start() {
        for (var thread : threads) {
            thread.start();
        }
    }

    public void stop() throws InterruptedException {
        for (var thread : threads) {
            thread.interrupt();
        }
        for (var thread : threads) {
            if (thread.isAlive())
            thread.join();
        }
    }

    public Storage<Accessory> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Engine> getEnginesStorage() {
        return enginesStorage;
    }

    public Storage<Body> getBodiesStorage() {
        return bodiesStorage;
    }

    public Storage<Car> getCarsStorage() {
        return carsStorage;
    }

    public CarBuilder getCarBuilder() {
        return carBuilder;
    }

    public ArrayList<Supplier<Accessory>> getAccessoriesSuppliers() {
        return accessoriesSuppliers;
    }

    public Supplier<Engine> getEnginesSupplier() {
        return enginesSupplier;
    }

    public Supplier<Body> getBodiesSupplier() {
        return bodiesSupplier;
    }

    public ArrayList<Dealer> getDealers() {
        return dealers;
    }
}
