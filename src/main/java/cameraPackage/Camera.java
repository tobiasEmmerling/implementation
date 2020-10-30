package cameraPackage;

public class Camera implements ICamera{
    private String serialNumber;
    private boolean isOn;
    private IRLed[] irLeds;
    private MemoryCard memoryCard;
    private Chip[] chips;

    Camera(Builder builder) {
        irLeds = builder.getIrLeds();
        memoryCard = builder.getMemoryCard();
        chips = builder.getChips();
        isOn = builder.isOn();
        serialNumber = builder.getSerialNumber();
    }
    @Override
    public void on() {
        isOn = true;
    }

    @Override
    public char[][] getRawFacePicture(int faceID) {
        return new char[0][];
    }

    @Override
    public int[] getFaceArea(char[][] face) {
        return new int[0];
    }

    @Override
    public Picture extractFace(int id, char[][] face, int[] area) {
        return null;
    }

    @Override
    public void off() {
        isOn = false;
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
