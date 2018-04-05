package co.edu.udea.softwaretesting.linescounter.models;

public class CountedFunctionData {

    private String name;
    private Integer locCountingFunction;

    public CountedFunctionData(String name, Integer locCountingFunction) {
        this.name = name;
        this.locCountingFunction = locCountingFunction;
    }

    public CountedFunctionData() {
        this.name = "";
        this.locCountingFunction = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocCountingFunction() {
        return locCountingFunction;
    }

    public void setLocCountingFunction(Integer locCountingFunction) {
        this.locCountingFunction = locCountingFunction;
    }
}
