package factory;


import carParts.Car;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardOpenOption.*;

public class Dealer implements Runnable {
    private final Storage<Car> carStorage;
    private Integer period;
    private boolean doLogging;
    private File file;

    public Dealer(Storage<Car> carStorage, Integer period, boolean doLogging) {
        this.carStorage = carStorage;
        this.period = period;
        this.doLogging = doLogging;

        if (doLogging) {
            file = new File("log.txt");
        }
    }

    public void run() {
        Car c;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                c = carStorage.get();
            } catch (InterruptedException exc) {
                break;
            }
            if (doLogging) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = dateFormat.format(new Date());
                String endString = date + ": Dealer " + Thread.currentThread().getName() + ": Auto " + c.getCarID() + " (Body: " + c.getBody().getPartID() + ", Engine: " + c.getEngine().getPartID() + ", Accessory: " + c.getAccessory().getPartID() + ")\n";
                synchronized (file) {
                    try {
                        Files.write(file.toPath(), endString.getBytes(), WRITE, APPEND, CREATE);
                    } catch (IOException e) {
                        System.err.println("Failed to log selling data. Turning off logging...");
                        doLogging = false;
                    }
                }
            }
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                return;
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

