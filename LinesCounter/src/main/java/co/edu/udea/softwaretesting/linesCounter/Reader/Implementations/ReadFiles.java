package co.edu.udea.softwaretesting.linesCounter.Reader.Implementations;

import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.FileData;
import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.Project;
import co.edu.udea.softwaretesting.linesCounter.Reader.Interfaces.ReadFilesInterface;
import co.edu.udea.softwaretesting.linesCounter.Resources.Configurations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFiles implements ReadFilesInterface {

    public Project projectData(String path) throws IOException {
        List<File> filesInFolder = filesInFolder(path);
        List<FileData> files = new ArrayList<>();
        filesInFolder.forEach((file) -> {
            files.add(readFile(file));
        });
        Project project = new Project(Configurations.REPO_NAME, files);

        return project;
    }

    public List<File> filesInFolder(String path) throws IOException {
        List<File> filesInFolder = new ArrayList<>();
        try {
            filesInFolder = Files.walk(Paths.get(path))
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch(IOException e) {
            System.err.println("Error reading files");
        }
        return filesInFolder;
    }

    public FileData readFile(File f) {
        List<String> lines = new ArrayList<>();
        FileData file;
        try (Stream<String> allLines = Files.lines(f.toPath())) {
            allLines.forEach(s -> lines.add(s));
        } catch (IOException e) {
            System.err.println("Error reading the file");
        } finally {
            file = new FileData(f.getName(), lines);
        }
        return file;
    }

}
