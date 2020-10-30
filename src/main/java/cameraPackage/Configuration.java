package cameraPackage;

public enum Configuration {

    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataFile = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "resources"+fileSeparator+"data"+fileSeparator+"face";
}
