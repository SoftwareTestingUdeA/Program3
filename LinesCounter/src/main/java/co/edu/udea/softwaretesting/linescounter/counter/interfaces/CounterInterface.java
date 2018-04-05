package co.edu.udea.softwaretesting.linescounter.counter.interfaces;

import co.edu.udea.softwaretesting.linescounter.models.CountedData;

import java.io.IOException;

public interface CounterInterface {

    CountedData getLOCCounting() throws IOException;

}
