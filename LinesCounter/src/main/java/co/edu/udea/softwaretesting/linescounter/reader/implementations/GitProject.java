package co.edu.udea.softwaretesting.linescounter.reader.implementations;

import co.edu.udea.softwaretesting.linescounter.reader.interfaces.GitProjectInterface;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class GitProject implements GitProjectInterface {

    public void cloneProject(String repoUrl, String cloneDirectoryPath) throws IOException {
        clearFolder(cloneDirectoryPath);
        Git repository = null;
        try {
            System.out.println("Cloning "+repoUrl+" into "+ cloneDirectoryPath);
            repository = Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                    .call();
            System.out.println("Completed Cloning");
        } catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        } finally {
            repository.close();
        }
    }

    public void clearFolder(String folderPath) throws IOException {
        if(checkIfFolerExist(folderPath)) {
            Files.walk(Paths.get(folderPath))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    private boolean checkIfFolerExist(String folderPath) {
        if(Files.exists(Paths.get(folderPath))) {
            return true;
        }
        return false;
    }
}
