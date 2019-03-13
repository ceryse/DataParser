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

    public static String[] getCleanLines(String data, int startingLine) {//2,3,4
        String[] rows = data.split("\n");
        String[] result = new String[rows.length];
        for (int i = startingLine; i < rows.length; i++) {
            rows[i] = removeUnnecessaryCommas(rows[i]);
            result[i - startingLine] = rows[i];
        }
        return result;
    }

    private static String removeUnnecessaryCommas(String row) {
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
}
