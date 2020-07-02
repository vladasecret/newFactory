package carParts;

import java.util.UUID;

public class Car {
    private final Body body;
    private final Accessory accessory;
    private final Engine engine;

    private final UUID carID;

    public Car(Body b, Accessory a, Engine e) {
        body = b;
        accessory = a;
        engine = e;

        carID = UUID.randomUUID();
    }

    public Body getBody() {
        return body;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public Engine getEngine() {
        return engine;
    }

    public UUID getCarID() {
        return carID;
    }
}
