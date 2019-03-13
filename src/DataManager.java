import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<State> states;

    public DataManager() {
        this.states = new ArrayList<>();
    }

    public void loadAllData(String electionFile, String educationFile, String employmentFile) {
        String[] educationRawLines = Utils.getCleanLines(educationFile, 6);
        String[] electionRawLines = Utils.getCleanLines(electionFile, 1);
        String[] employmentRawLines = Utils.getCleanLines(employmentFile, 9);

        addStateObjs(electionRawLines);
        addCountyObjs(electionRawLines, educationRawLines, employmentRawLines);
    }

    private void loadEducationData(String[] lines, County c) {
        for (int i = 0; i < lines.length - 1; i++) {
            String line = lines[i];
            if (line == null) continue;
            String[] vals = line.split(",");
            if (vals.length < 43) continue;
            c.setEduc2016(new Education2016(Double.parseDouble(vals[43].trim()), Double.parseDouble(vals[44].trim()), Double.parseDouble(vals[45].trim()), Double.parseDouble(vals[46].trim()), c));
        }
    }

    private void loadEmploymentData(String[] lines, County c) {
        for (int i = 0; i < lines.length - 1; i++) {
            String line = lines[i];
            if (line == null) continue;
            String[] vals = line.split(",");
            if (vals.length < 42) continue;
            c.setEmploy2016(new Employment2016(Integer.parseInt(vals[42].trim()), Integer.parseInt(vals[43].trim()), Integer.parseInt(vals[44].trim()), Double.parseDouble(vals[45].trim()), c));
        }
    }

    private void addCountyObjs(String[] electionLines, String[] educationLines, String[] employmentLines) {
        for (String line : electionLines) {
            if (line == null) continue;
            String[] vals = line.split(",");
            String stateName = vals[8];

            State state = getState(stateName);
            if (state == null) {
                System.out.println("ERROR: state is null");
            } else {
                String countyName = vals[9];
                int fips = Integer.parseInt(vals[10]);
                County c = new County(countyName, fips, state);
                c.setVote2016(new Election2016(Double.parseDouble(vals[1].trim()), Double.parseDouble(vals[2].trim()), Double.parseDouble(vals[3].trim()), c));
                loadEducationData(educationLines, c);
                loadEmploymentData(employmentLines, c);
                state.add(c);
            }
        }
    }

    private State getState(String stateName) {
        for (State state : states) {
            if (state.getName().equals(stateName)) return state;
        }
        return null;
    }

    private void addStateObjs(String[] lines) {
        for (int i = 0; i < lines.length - 1; i++) {
            String line = lines[i];
            String[] vals = line.split(",");
            State state = new State(vals[8].trim());
            if (!containsState(state)) {
                states.add(state);
            }
        }
    }

    private boolean containsState(State state) {
        for (State s : states) {
            if (s.getName().equals(state.getName())) return true;
        }
        return false;
    }

    public void add(State state) {
        states.add(state);
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
