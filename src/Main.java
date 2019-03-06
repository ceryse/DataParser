import java.util.ArrayList;

/***
 * Main class for DataParser
 * @author: Cerys Edwards
 */

public class Main {
    public static void main(String[] args) {
        //test of Utils

        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");

        ArrayList<ElectionResult> results = Utils.parse2016ElectionResults(data);

        for (ElectionResult result : results)System.out.println(result);
    }
}
