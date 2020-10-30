import cameraPackage.Builder;
import cameraPackage.Camera;
import cameraPackage.MemoryCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;


public class TestCamera {

    private Builder builder;
    private Camera camera;

    @BeforeEach
    public void init(){
        MemoryCard memoryCard = new MemoryCard();
        builder = new Builder(memoryCard);
        camera = builder.build();
    }

    @Test
    public void checkIfBuilderIsCorrect(){
        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(camera.getChips()[0]);
        Assertions.assertNotNull(camera.getChips()[1]);
        for (int i = 0; i < 24; i++) {
            Assertions.assertNotNull(camera.getIrLeds()[i]);
        }
        Assertions.assertNotNull(camera.getSerialNumber());
        Assertions.assertEquals(Camera.class ,camera.getClass());
    }
}
