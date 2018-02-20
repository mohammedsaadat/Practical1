package cs5031;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Word {

    static final int COUNTIES = 1;
    static final int COUNTRIES = 2;
    static final int CITIES = 3;


    static final String[] COUNTIES_WORDS = {"Argyll and Bute", "Caithness", "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross"};
    static final String[] COUNTRIES_WORDS = {"Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};
    static final String[] CITIES_WORDS = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};

    private static ArrayList<String> customWords;
    private static Random random = new Random();

    public static String randomWord(int category) {
        if (category == COUNTIES)
            return COUNTIES_WORDS[(random.nextInt(9))];
        else if (category == COUNTRIES)
            return COUNTRIES_WORDS[random.nextInt(15)];
        else if (category == CITIES)
            return CITIES_WORDS[random.nextInt(10)];
        else
            return "Wrong Input";
    }

    public static String randomWord(String wordSource) {
        String line;
        customWords = new ArrayList<>();
        try {
            FileReader file = new FileReader(wordSource);
            BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                customWords.add(line);
            }

            reader.close();
            return customWords.get((int) (Math.random() * customWords.size()));
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch (IOException e) {
            System.out.println("IO error");
            return "";
        }
    }
}
