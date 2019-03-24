import java.util.ArrayList;

/***
 * Main class for DataParser
 * @author: Cerys Edwards
 */

public class Main {
    public static void main(String[] args) {
        String voterData = Utils.readFileAsString("data/RegisteredVoters.csv");
        String householdIncome = Utils.readFileAsString("data/MedianHouseholdIncome.csv");

        DataManager dataManager = new DataManager();
        dataManager.loadAllData(householdIncome, voterData);
        System.out.println(dataManager.getMedianIncome());
        System.out.println("State\t% Voted out of total Pop.\tMedian income\tHigh income?");
        for (State state : dataManager.getStates()) {
            System.out.println(state.getName() + "\t" + state.getData().getPercentVoted() + "\t" + state.getData().getMedianIncome() + "\t" + state.getData().isHighIncome());
        }
    }
}
