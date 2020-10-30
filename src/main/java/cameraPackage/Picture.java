package cameraPackage;

public class Picture {

    private int faceID;
    private char[][] content = new char[10][10];
    private long nanoTimeStamp;

    public Picture(int faceID, char[][] content){
        this.faceID = faceID;
        this.content = content;
        nanoTimeStamp = System.nanoTime();
    }

    public char[][] getContent() {
        return content;
    }
}
