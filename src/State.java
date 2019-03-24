public class State {
    private String name;
    private Data data;

    public State(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
