import javax.xml.crypto.Data;
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
        while (row.contains("\"")) {
            int indexOfQuote = row.indexOf("\"");
            int indexOf2ndQuote = row.indexOf("\"", indexOfQuote + 1);
            String a = row.substring(indexOfQuote, indexOf2ndQuote + 1);
            String b = a.replace(",", "");
            b = b.substring(1, b.length() - 1);
            row = row.replace(a, b);
        }
        return row;
    }

    public static ArrayList<Education2016> parse2016Education(String data) {
        ArrayList<Education2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        for (int i = 6; i < rows.length; i++) {
            rows[i] = removeComma(rows[i]);
            String[] list = rows[i].split(",");
            results.add(new Education2016(Double.parseDouble(list[44]), Double.parseDouble(list[45]), Double.parseDouble(list[46]), Double.parseDouble(list[47])));
        }
        return results;
    }

    public static ArrayList<Employment2016> parse2016Employment(String data) {
        ArrayList<Employment2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        for (int i = 9; i < rows.length; i++) {
            rows[i] = removeComma(rows[i]);
            String[] list = rows[i].split(",");
            results.add(new Employment2016(Integer.parseInt(list[43]), Integer.parseInt(list[44]), Integer.parseInt(list[45]), Integer.parseInt(list[46])));
        }
        return results;
    }
}
