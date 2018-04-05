package co.edu.udea.softwaretesting.linescounter.models;

import java.util.List;

public class CountedClassData {

    private String name;
    private List<CountedFunctionData> locCountingFunctions;
    private Integer locCountingProperties;

    public CountedClassData(String name, List<CountedFunctionData> locCountingFunctions, Integer locCountingProperties) {
        this.name = name;
        this.locCountingFunctions = locCountingFunctions;
        this.locCountingProperties = locCountingProperties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountedFunctionData> getLocCountingFunctions() {
        return locCountingFunctions;
    }

    public void setLocCountingFunctions(List<CountedFunctionData> locCountingFunctions) {
        this.locCountingFunctions = locCountingFunctions;
    }

    public Integer getLocCountingProperties() {
        return locCountingProperties;
    }

    public void setLocCountingProperties(Integer locCountingproperties) {
        this.locCountingProperties = locCountingproperties;
    }
}
