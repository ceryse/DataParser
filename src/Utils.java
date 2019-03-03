import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> results = new ArrayList<>();
        String[] rows = data.split("\n");
        for (int i = 1; i < rows.length; i++) {
            rows[i] = removeComma(rows[i]);
            String[] list = rows[i].split(",");
            results.add(new ElectionResult(list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8], list[9], list[10]));
        }
        return results;
    }

    private static String removeComma(String row) {
        if (row.contains("\"")) {
            int indexOfQuote = row.indexOf("\"");
            int indexOf2ndQuote = row.indexOf("\"", indexOfQuote + 1);
            String a = row.substring(indexOfQuote, indexOf2ndQuote + 1);
            String b = a.replace(",", " ");
            row = row.replace(a, b);
        }
        return row;
    }
}
