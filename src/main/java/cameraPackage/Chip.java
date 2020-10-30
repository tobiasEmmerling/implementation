package cameraPackage;

public class Chip {
    private String uuid;
    private Core[] cores = new Core[2];
    public Chip(){
        cores[0] = new Core();
        cores[1] = new Core();
    }
}
