package co.edu.udea.softwaretesting.linescounter.reader.interfaces;

import java.io.IOException;

public interface GitProjectInterface {

    void cloneProject(String repoUrl, String cloneDirectoryPath) throws IOException;
    void clearFolder(String folderPath) throws IOException;

}
