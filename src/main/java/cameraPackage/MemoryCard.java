package cameraPackage;

import java.util.Stack;

public class MemoryCard {
    private Stack<Picture> store;

    public MemoryCard(){
        store = new Stack<>();
    }

    public void storePicture(Picture picture){
        store.add(picture);
    }

    public Stack<Picture> getStore() {
        return store;
    }
}
