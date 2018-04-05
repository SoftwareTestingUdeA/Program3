package co.edu.udea.softwaretesting.linescounter.resources;

public class Configurations {

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RELATIVE_PROJECT_PATH = PROJECT_PATH.substring(0, PROJECT_PATH.indexOf("LinesCounter"));
    public static final String CLONED_DIRECTORY_PATH = RELATIVE_PROJECT_PATH  + "ClonedProject";
    public static final String REPO_NAME = "Programa2";
    public static final String REPO_URL = "https://github.com/SoftwareTestingUdeA/" + REPO_NAME;

}
