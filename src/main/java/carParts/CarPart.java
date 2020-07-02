package carParts;

import java.util.UUID;

public class CarPart {
    private UUID partID;

    public CarPart() {
        generatePartID();
    }

    public void generatePartID() {
        partID = UUID.randomUUID();
    }

    public UUID getPartID() {
        return partID;
    }
}
