package cameraPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Camera implements ICamera{
    private String serialNumber;
    private boolean isOn;
    private IRLed[] irLeds;
    private MemoryCard memoryCard;
    private Chip[] chips;

    public Camera(Builder builder) {
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
        String faceIDString;
        if(faceID < 10){
            faceIDString = "0"+faceID;
        }else{
            faceIDString = String.valueOf(faceID);
        }
        char[][] rawPicture = new char[21][14];
        int column = 0;
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader(new File(Configuration.instance.dataFile+faceIDString+".txt")));
            while((line = br.readLine()) != null){
                for (int i = 0; i < line.length(); i++) {
                    rawPicture[column][i] = line.charAt(i);
                }
                column++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rawPicture;
    }

    @Override
    public int[] getFaceArea(char[][] face) {
        boolean found = false;
        int position = 0;
        int[] areaOfFace = new int[4];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 14; j++) {
                //set Pos of first +
                if(face[i][j] == '+' && !found){
                    areaOfFace[0] = Integer.valueOf(i);
                    areaOfFace[1] = Integer.valueOf(j)+1;
                    found = true;
                }
                //Set Pos of last +
                if(face[i][j] == '+'){
                    areaOfFace[2] = Integer.valueOf(i);
                    areaOfFace[3] = Integer.valueOf(j);
                }
            }
        }
        return areaOfFace;
    }

    @Override
    public Picture extractFace(int id, char[][] face, int[] area) {
        char[][] extractedFace = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                extractedFace[i][j] = face[area[0]+i][area[1]+j];
            }
        }
        Picture picture = new Picture(id, extractedFace);
        memoryCard.storePicture(picture);
        return picture;
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

    public static class Builder {
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

        public Camera build(){
            return new Camera(this);
        }
    }
}
