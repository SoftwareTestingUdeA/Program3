package co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned;

import java.util.List;

public class Project {

    private String name;
    private List<FileData> files;

    public Project(String name, List<FileData> files) {
        this.name = name;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileData> getFiles() {
        return files;
    }

    public void setFiles(List<FileData> files) {
        this.files = files;
    }
}
