package co.edu.udea.softwaretesting.linesCounter.Counter.Interfaces;

import co.edu.udea.softwaretesting.linesCounter.Models.CountedData;

import java.io.IOException;

public interface CounterInterface {

    CountedData getLOCCounting() throws IOException;

}
