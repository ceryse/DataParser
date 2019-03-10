import javax.xml.crypto.Data;
import java.util.ArrayList;

/***
 * Main class for DataParser
 * @author: Cerys Edwards
 */

public class Main {
    public static void main(String[] args) {

        String presidentialData = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        String educationData = Utils.readFileAsString("data/Education.csv");
        String employmentData = Utils.readFileAsString("data/Unemployment.csv");

        ArrayList<ElectionResult> electionResults = Utils.parse2016ElectionResults(presidentialData);

        ArrayList<String> stateNames = new ArrayList<>();
        ArrayList<String> countyNames = new ArrayList<>();
        ArrayList<Integer> countyFips = new ArrayList<>();
        ArrayList<State> states = new ArrayList<>();

        for (ElectionResult result : electionResults) {
            String stateName = result.getState_abbr();
            String countyName = result.getCounty_name();
            countyNames.add(countyName);
            countyFips.add(result.getCombined_fips());
            if (!stateNames.contains(stateName)) {
                stateNames.add(stateName);
            }
        }

        for (String name : stateNames) {
            states.add(new State(name));
        }
        DataManager dataManager = new DataManager(states);

        for (int i = 0; i < countyNames.size(); i++) {
            String stateName = electionResults.get(i).getState_abbr();
            String name = countyNames.get(i);
            int fips = countyFips.get(i);
            Education2016 education = Utils.getEducation(name, educationData);
            Employment2016 employment = Utils.getEmployment(name, employmentData);
            Election2016 election = new Election2016(electionResults.get(i).getVotes_dem(), electionResults.get(i).getVotes_gop(), electionResults.get(i).getTotal_votes());
            County c = new County(name, fips, election, education, employment);
            for (State state : dataManager.getStates()) {
                if (state.getName().equals(stateName)) {
                    state.add(c);
                }
            }
        }
    }
}
