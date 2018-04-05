package co.edu.udea.softwaretesting.linesCounter.Reader.Interfaces;

import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.FileData;
import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.Project;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReadFilesInterface {

    List<File> filesInFolder(String path) throws IOException;
    FileData readFile(File file);
    Project projectData(String path) throws IOException;

}
