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

        DataManager dataManager = new DataManager();
        dataManager.loadAllData(presidentialData, educationData, employmentData);
    }
}
