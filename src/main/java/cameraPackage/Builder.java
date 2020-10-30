package cameraPackage;

public class Builder {
    private String serialNumber;
    private boolean isOn;
    private IRLed[] irLeds;
    private MemoryCard memoryCard;
    private Chip[] chips;

    public Builder(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
        irLeds = new IRLed[24];
        for (int i = 0; i < 24; i++) {
            irLeds[i] = new IRLed();
        }
        chips = new Chip[2];
        for (int i = 0; i < 2; i++) {
            chips[i] = new Chip();
        }
        isOn = false;
        serialNumber = "187";
    }
    public Camera build(){
        return new Camera(this);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isOn() {
        return isOn;
    }

    public IRLed[] getIrLeds() {
        return irLeds;
    }

    public MemoryCard getMemoryCard() {
        return memoryCard;
    }

    public Chip[] getChips() {
        return chips;
    }
}
