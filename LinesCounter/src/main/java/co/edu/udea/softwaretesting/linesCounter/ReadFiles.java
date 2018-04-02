package co.edu.udea.softwaretesting.linesCounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFiles {

    public static List<File> filesInFolder(String path) throws IOException {
        List<File> filesInFolder = new ArrayList<>();
        try {
            filesInFolder = Files.walk(Paths.get(path))
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch(IOException e) {
        }
        return filesInFolder;
    }

    public static void readFile(File file) {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            lines.forEach(s -> System.out.println(s));
        } catch (IOException e) {
        }
    }

}
