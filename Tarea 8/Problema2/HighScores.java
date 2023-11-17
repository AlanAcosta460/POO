import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;

public class HighScores {
    private static final int MAX_SCORES = 10;
    private static HashMap<String, Double> scores = new HashMap<>();

    public static boolean canInsert(double score) {
        scores.clear();
        try (Scanner reader = new Scanner(new File("hs.txt"))) {
            while (reader.hasNextLine() && scores.size() < MAX_SCORES) {
                String line = reader.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " - ");
                scores.put(tokenizer.nextToken(), Double.parseDouble(tokenizer.nextToken()));
            }
        } catch (IOException e) {}

        if (scores.size() < MAX_SCORES) {
            return true;
        } else {
            double minScore = 999999999;
            String minName = "";
            for (String key : scores.keySet()) {
                if (scores.get(key) < minScore) {
                    minScore = scores.get(key);
                    minName = key;
                }
            }
            if (score > minScore) {
                scores.remove(minName);
                return true;
            }
        }
        return false;
    }

    public static void insert(String name, double score) {
        scores.put(name, score);
    }

    public static void save() {
        try (PrintWriter writer = new PrintWriter("hs.txt")) {
            for (String key : scores.keySet()) {
                writer.println(key + " - " + scores.get(key));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los puntajes");
        }
    }
}