package co.edu.udea.softwaretesting.linesCounter;

import co.edu.udea.softwaretesting.linesCounter.Counter.Implementations.Counter;
import co.edu.udea.softwaretesting.linesCounter.Counter.Interfaces.CounterInterface;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedClassData;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedData;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedFunctionData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LinesCounterApplication {

	private static CounterInterface counter;
	private static CountedData data;

	public static void main(String[] args)  throws IOException {
		SpringApplication.run(LinesCounterApplication.class, args);

		counter = new Counter();
		data = counter.getLOCCounting();

		System.out.println("El nombre del proyecto es: " + data.getName());
		System.out.println();

		long totalCount = 0;

		for (CountedClassData classData : data.getLoc()) {
			long count = 0;
			System.out.println("El nombre de la clase es: " + classData.getName());
			for (CountedFunctionData functionData : classData.getLocCountingFunctions()) {
				System.out.println("* El nombre de la función es: " + functionData.getName());
				System.out.println("* Tiene un LOC de: " + functionData.getLocCountingFunction());
				count += functionData.getLocCountingFunction();
			}
			count += classData.getLocCountingProperties();
			System.out.println("El LOC de la clase en total es: " + count);
			totalCount += count;
		}
		System.out.println("El LOC total de la aplicación es: " + totalCount);
	}
}
