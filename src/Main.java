/***
 * Main class for DataParser
 * @author: Cerys Edwards
 */

public class Main {
    public static void main(String[] args) {
        //test of Utils

        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        System.out.println(data);
    }
}
