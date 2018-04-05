package co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned;

import java.util.List;

public class FileData {

    private String name;
    private List<String> lines;

    public FileData(String name, List<String> lines) {
        this.name = name;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
