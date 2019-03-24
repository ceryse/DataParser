import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<State1> state1s;

    public DataManager() {
        this.state1s = new ArrayList<>();
    }

    public void loadAllData(String incomeFile, String votersFile) {
        String[] incomeRawLines = Utils.getCleanLines(incomeFile, 2);
        String[] voterRawLines = Utils.getCleanLines(votersFile, 11);

        addStateObjs(incomeRawLines, voterRawLines);
    }

    private void addStateObjs(String[] incomeRawLines, String[] voterRawLines) {
        for (int i = 0; i < incomeRawLines.length; i++) {
            String voterLine = voterRawLines[i];
            String incomeLine = incomeRawLines[i];
            if (voterLine == null) System.out.println("voter line null");
            if (incomeLine == null) System.out.println("income line null");
            String[] incomeVals = incomeLine.split(",");
            String[] voterVals = voterLine.split(",");
            Data data = new Data(incomeVals[1], voterVals[9], incomeVals[11]);
            State state = new State(data.getState(), data);
        }
    }

    public void loadAllData(String electionFile, String educationFile, String employmentFile) {
        String[] educationRawLines = Utils.getCleanLines(educationFile, 6);
        String[] electionRawLines = Utils.getCleanLines(electionFile, 1);
        String[] employmentRawLines = Utils.getCleanLines(employmentFile, 9);

        addStateObjs(electionRawLines);
        addCountyObjs(electionRawLines, educationRawLines, employmentRawLines);
    }

    private void loadEducationData(String[] lines, County c) {
        for (String line : lines) {
            if (line == null) continue;
            String[] vals = line.split(",");
            if (vals.length < 43) continue;
            if (vals[2].equals(c.getName())) {
                c.setEduc2016(new Education2016(Double.parseDouble(vals[43].trim()), Double.parseDouble(vals[44].trim()), Double.parseDouble(vals[45].trim()), Double.parseDouble(vals[46].trim()), c));
            }
        }
    }

    private void loadEmploymentData(String[] lines, County c) {
        for (String line : lines) {
            if (line == null) continue;
            String[] vals = line.split(",");
            if (vals.length < 42) continue;
            if (vals[2].equals(c.getName())) {
                c.setEmploy2016(new Employment2016(Integer.parseInt(vals[42].trim()), Integer.parseInt(vals[43].trim()), Integer.parseInt(vals[44].trim()), Double.parseDouble(vals[45].trim()), c));
            }
        }
    }

    private void addCountyObjs(String[] electionLines, String[] educationLines, String[] employmentLines) {
        for (String line : electionLines) {
            if (line == null) continue;
            String[] vals = line.split(",");
            String stateName = vals[8];

            State1 state1 = getState(stateName);
            if (state1 == null) {
                System.out.println("ERROR: state1 is null");
            } else {
                String countyName = vals[9];
                int fips = Integer.parseInt(vals[10]);
                County c = new County(countyName, fips, state1);
                c.setVote2016(new Election2016(Double.parseDouble(vals[1].trim()), Double.parseDouble(vals[2].trim()), Double.parseDouble(vals[3].trim()), c));
                loadEducationData(educationLines, c);
                loadEmploymentData(employmentLines, c);
                state1.add(c);
            }
        }
    }

    private State1 getState(String stateName) {
        for (State1 state1 : state1s) {
            if (state1.getName().equals(stateName)) return state1;
        }
        return null;
    }

    private void addStateObjs(String[] lines) {
        for (String line : lines) {
            if (line == null) continue;
            String[] vals = line.split(",");
            State1 state1 = new State1(vals[8].trim());
            if (!containsState(state1)) {
                state1s.add(state1);
            }
        }
    }

    private boolean containsState(State1 state1) {
        for (State1 s : state1s) {
            if (s.getName().equals(state1.getName())) return true;
        }
        return false;
    }

    public void add(State1 state1) {
        state1s.add(state1);
    }

    public List<State1> getState1s() {
        return state1s;
    }

    public void setState1s(List<State1> state1s) {
        this.state1s = state1s;
    }
}
