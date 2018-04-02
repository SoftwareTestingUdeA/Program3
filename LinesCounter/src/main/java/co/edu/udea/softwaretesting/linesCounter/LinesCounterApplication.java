package co.edu.udea.softwaretesting.linesCounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class LinesCounterApplication {

	private static String repoUrl = "https://github.com/SoftwareTestingUdeA/StatisticsService";
	private static String cloneDirectoryPath = "/home/jpoh97/Documentos/Pruebas/Program3/ClonedProject";

	public static void main(String[] args)  throws IOException {
		SpringApplication.run(LinesCounterApplication.class, args);
		GitProject.cloneProject(repoUrl, cloneDirectoryPath);

		List<File> filesInFolder = ReadFiles.filesInFolder(cloneDirectoryPath);

		filesInFolder.forEach((file) -> {
			ReadFiles.readFile(file);
		});
	}
}
