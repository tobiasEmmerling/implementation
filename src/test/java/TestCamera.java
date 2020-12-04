import cameraPackage.Camera;
import cameraPackage.MemoryCard;
import cameraPackage.Camera.Builder;
import cameraPackage.Picture;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;


public class TestCamera {

    private Builder builder;
    private Camera camera;

    @BeforeEach
    public void init() {
        MemoryCard memoryCard = new MemoryCard();
        builder = new Builder(memoryCard);
        camera = builder.build();
    }

    //Number 1
    @Order(1)
    @Test
    @DisplayName("builder generates camera correctly")
    public void checkIfBuilderIsCorrect() {
        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(camera.getChips()[0]);
        Assertions.assertNotNull(camera.getChips()[1]);
        for (int i = 0; i < 24; i++) {
            Assertions.assertNotNull(camera.getIrLeds()[i]);
        }
        Assertions.assertNotNull(camera.getSerialNumber());
        Assertions.assertEquals(Camera.class, camera.getClass());
        Assertions.assertNotNull(camera.getMemoryCard());
    }

    @Order(2)
    @Test
    @DisplayName("should enable and disable the camera")
    public void checkIfOnOffWorks() {
        Assertions.assertFalse(camera.isOn());
        camera.on();
        Assertions.assertTrue(camera.isOn());
        camera.off();
        Assertions.assertFalse(camera.isOn());
    }

    @Order(3)
    @TestFactory
    @DisplayName("get coordinates of faceArea")
    Stream<DynamicTest> dynamicTestsExample() {
        List<Integer> inputList01 = Arrays.asList(2, 2, 11, 11);
        List<Integer> inputList02 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            inputList02.add(camera.getFaceArea(camera.getRawFacePicture(1))[i]);
        }

        List<DynamicTest> dynamicTestList = new ArrayList<>();

        for (int i = 0; i < inputList01.size(); i++) {
            int x = inputList01.get(i);
            int y = inputList02.get(i);

            DynamicTest dynamicTest = dynamicTest("dynamic test for checkGetFaceArea(" + x + "," + y + ")", () -> {
                assertEquals(x, y);
            });

            dynamicTestList.add(dynamicTest);
        }

        return dynamicTestList.stream();
    }

    @Order(4)
    @Test
    @DisplayName("extract face")
    public void checkExtractFace() {
        int faceID = 1;
        Assertions.assertNotNull(camera.extractFace(faceID, camera.getRawFacePicture(faceID), camera.getFaceArea(camera.getRawFacePicture(faceID))));
        Assertions.assertEquals(1, camera.getMemoryCard().getStore().size());
    }

    @Order(5)
    @Test
    @DisplayName("picture saved correctly")
    public void checkContent() {
        int faceID = 1;
        Picture picture = camera.extractFace(faceID, camera.getRawFacePicture(faceID), camera.getFaceArea(camera.getRawFacePicture(faceID)));
        Assertions.assertEquals(camera.getMemoryCard().getStore().peek(), picture);
        Assertions.assertEquals(camera.getMemoryCard().getStore().peek().getContent().getClass(), char[][].class);
    }
}
