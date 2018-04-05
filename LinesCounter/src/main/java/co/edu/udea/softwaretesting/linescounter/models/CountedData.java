package co.edu.udea.softwaretesting.linescounter.models;

import java.util.ArrayList;
import java.util.List;

public class CountedData {

    private String name;
    private List<CountedClassData> loc;

    public CountedData() {
        this.name = "";
        this.loc = new ArrayList<>();
    }

    public CountedData(String name, List<CountedClassData> loc) {
        this.name = name;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountedClassData> getLoc() {
        return loc;
    }

    public void setLoc(List<CountedClassData> loc) {
        this.loc = loc;
    }
}
