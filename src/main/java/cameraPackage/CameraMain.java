package cameraPackage;

public class CameraMain {
    public static void main(String[] args) {
        MemoryCard memoryCard = new MemoryCard();
        Camera.Builder builder = new Camera.Builder(memoryCard);
        Camera camera = builder.build();
        System.out.println(camera.getFaceArea(camera.getRawFacePicture(1))[0]+ ", "+camera.getFaceArea(camera.getRawFacePicture(1))[1]+", "+camera.getFaceArea(camera.getRawFacePicture(1))[2]+", "+camera.getFaceArea(camera.getRawFacePicture(1))[3]);
    }
}
