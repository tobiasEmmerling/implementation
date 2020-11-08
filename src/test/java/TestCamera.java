import cameraPackage.Camera;
import cameraPackage.MemoryCard;
import cameraPackage.Camera.Builder;
import cameraPackage.Picture;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


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
    @Test
    public void checkIfBuilderIsCorrect() {
        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(camera.getChips()[0]);
        Assertions.assertNotNull(camera.getChips()[1]);
        for (int i = 0; i < 24; i++) {
            Assertions.assertNotNull(camera.getIrLeds()[i]);
        }
        Assertions.assertNotNull(camera.getSerialNumber());
        Assertions.assertEquals(Camera.class, camera.getClass());
    }

    //Number 2
    @Test
    public void checkIfOnOffWorks() {
        Assertions.assertFalse(camera.isOn());
        camera.on();
        Assertions.assertTrue(camera.isOn());
        camera.off();
        Assertions.assertFalse(camera.isOn());
    }

    //Number 3
//    @TestFactory
//    Stream<DynamicTest> dynamicTestStream(){
//        List<Integer> inputList1 = Arrays.asList();
//        List<DynamicTest> dynamicTests = new ArrayList<>();
//        return
//    }
    @Test
    public void checkGetFaceArea() {
        int[] coordinates = {2, 2, 11, 11};
        for (int i = 0; i < 4; i++) {
            Assertions.assertEquals(coordinates[i], camera.getFaceArea(camera.getRawFacePicture(1))[i]);
        }
    }

    //Number 4
    @Test
    public void checkExtractFace() {
        int faceID = 1;
        Assertions.assertNotNull(camera.extractFace(faceID, camera.getRawFacePicture(faceID), camera.getFaceArea(camera.getRawFacePicture(faceID))));
        Assertions.assertEquals(1, camera.getMemoryCard().getStore().size());
    }

    //Number 5
    @Test
    public void checkContent() {
        int faceID = 1;
        Picture picture = camera.extractFace(faceID, camera.getRawFacePicture(faceID), camera.getFaceArea(camera.getRawFacePicture(faceID)));
        Assertions.assertEquals(camera.getMemoryCard().getStore().peek(), picture);
        Assertions.assertEquals(camera.getMemoryCard().getStore().peek().getContent().getClass(), char[][].class);
    }
}
