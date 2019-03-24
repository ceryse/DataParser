import java.util.ArrayList;

/***
 * Main class for DataParser
 * @author: Cerys Edwards
 */

public class Main {
    public static void main(String[] args) {


        String presidentialData = Utils.readFileAsString("Data/2016_Presidential_Results.csv");
        String educationData = Utils.readFileAsString("Data/Education.csv");
        String employmentData = Utils.readFileAsString("Data/Unemployment.csv");
        String voterData = Utils.readFileAsString("Data/RegisteredVoters.csv");
        String householdIncome = Utils.readFileAsString("Data/MedianHouseholdIncome.csv");


        DataManager dataManager = new DataManager();
        dataManager.loadAllData(householdIncome, voterData);
    }
}
