package co.edu.udea.softwaretesting.linescounter.reader.interfaces;

import co.edu.udea.softwaretesting.linescounter.models.projectcloned.FileData;
import co.edu.udea.softwaretesting.linescounter.models.projectcloned.Project;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReadFilesInterface {

    List<File> filesInFolder(String path) throws IOException;
    FileData readFile(File file);
    Project projectData(String path) throws IOException;

}
