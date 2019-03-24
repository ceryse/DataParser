import java.util.ArrayList;
import java.util.List;

public class State1 {
    private String name;
    private List<County> counties;

    public State1(String name) {
        this.name = name;
        counties = new ArrayList<>();
    }



    public void add(County c) {
        counties.add(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }
}
